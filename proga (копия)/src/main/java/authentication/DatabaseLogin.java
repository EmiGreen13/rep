package authentication;

import entity.Role;
import entity.User;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import service.UserDao;

import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

public class DatabaseLogin implements LoginModule {

    private SimpleGroup group = new SimpleGroup("Roles");
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Principal principal;
    private boolean loginOk = false;
    private boolean authenticated = false;
    private String username;
    private User user;

    public void initialize(Subject subject, CallbackHandler callbackHandler,Map sharedState, Map options){
        this.sharedState = sharedState;
        this.callbackHandler = callbackHandler;
        this.subject = subject;
    }

    public boolean login() throws LoginException
    {
        if (!authenticated) {
            try {
                UserDao service = (UserDao) new InitialContext().lookup("java:global/proga/UserDao!service.UserDao");
                if (service == null) {
                    return false;
                }
                //request = (HttpServletRequest) PolicyContext.getContext(HttpServletRequest.class.getName());
                username = getUsernameAndPassword()[0];
                String password = getUsernameAndPassword()[1];
                //if null
                if (username == null || password == null) {
                    return false;
                }
                user = service.findByUsername(username);
                if (user == null) {
                    return false;
                }

                String hashPassword = HashPassword.getHashPassword(username, password);
                if (hashPassword == null) {
                    return false;
                }

                if (user.getPassword().equals(hashPassword)) {
                    Callback[] calls = new Callback[2];
                    calls[0] = new NameCallback(username);
                    calls[1] = new PasswordCallback(password, true);
                    hashMap("javax.security.auth.login.name", username);
                    hashMap("javax.security.auth.login.password", password);
                    callbackHandler.handle(calls);
                    loginOk = true;
                    return true;
                }
                return false;
            } catch (Exception exception) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void hashMap(String key, String value){
        sharedState.put(key, value);
    }

    public boolean abort() throws LoginException{
        username = null;
        principal = null;
        subject = null;
        group = null;
        authenticated = false;
        return true;
    }

    public boolean commit()throws LoginException{
        try{
            if (loginOk & !authenticated){
                for (Role role: user.getRoles()) {
                    //[admin, user]
                    principal = new SimplePrincipal(role.getRole());
                    group.addMember(principal);
                }
                subject.getPrincipals().add(new SimplePrincipal(username));
                subject.getPrincipals().add(group);
                authenticated = true;
            }
            return true;
        }
        catch (Exception exeption){
            exeption.printStackTrace();
            throw new LoginException(exeption.getMessage());
        }
    }

    public boolean logout() throws LoginException {
        if (loginOk & authenticated){
            subject.getPrincipals().remove(new SimplePrincipal(username));
            subject.getPrincipals().remove(group);
            username = null;
            group = null;
            loginOk = false;
            authenticated = false;
        }
        return true;
    }

    private String[] getUsernameAndPassword() throws LoginException{
        String[] info = {null, null};
        if( callbackHandler == null ){
            throw new LoginException("Error: no CallbackHandler available " +
                    "to collect authentication information");
        }
        NameCallback nc = new NameCallback("User name: ", "guest");
        PasswordCallback pc = new PasswordCallback("Password: ", false);
        Callback[] callbacks = {nc, pc};
        String username;
        String password = null;
        try{
            callbackHandler.handle(callbacks);
            username = nc.getName();
            char[] tmpPassword = pc.getPassword();
            if( tmpPassword != null){
                char[] credential = new char[tmpPassword.length];
                System.arraycopy(tmpPassword, 0, credential, 0, tmpPassword.length);
                pc.clearPassword();
                password = new String(credential);
            }
        }
        catch(IOException ioe){
            throw new LoginException(ioe.toString());
        }
        catch(UnsupportedCallbackException uce){
            throw new LoginException("CallbackHandler does not support: " + uce.getCallback());
        }
        info[0] = username;
        info[1] = password;
        return info;
    }
}
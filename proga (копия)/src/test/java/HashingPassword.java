import authentication.HashPassword;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.EJBException;


@RunWith(JUnit4.class)
public class HashingPassword {


    @Test
    public void assertHash() throws EJBException {
        try {
            Assert.assertEquals(HashPassword.getHashPassword("12", "12"), "6A4CE5128696F56855F1D8E1B5626608B77DBE34");
            System.out.println("Верно");
        }
        catch (Exception error){
            System.out.println("Ошибка");
        }
    }

    @Test
    public void assertHashWrong() throws EJBException {
        try {
            Assert.assertNotEquals(HashPassword.getHashPassword("12", "12"), "6A7CE5128696F56855F1D8E1B5626608B77DBE34");
            System.out.println("Не равно");
        }
        catch (Exception error){
            System.out.println("Ошибка");
        }
    }
}
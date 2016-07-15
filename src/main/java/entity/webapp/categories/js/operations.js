var added = function () {
    var title = $("#title").val();

    if (title.length == 0 || !titleMatches(title)){
        exception("Ошибка. Неверный формат данных.");
        return -1;
    }
    $.ajax({
        type: 'post',
        url: 'add',
        data: {'title': title},
        dataType: 'json',
        success: function (data) {
            exception(data);
            $("#title").val("");
            selectAll();
        },
        error: function (rsp) {
            var error = JSON.parse(rsp.responseText);
            exception(error);
        }
    });

};

var exception = function (msg) {
    alert(msg);
};

var changes = function (position, id) {
    var title = $(position).val();

    if (title.length == 0 || !titleMatches(title)){
        exception("Ошибка. Неверный формат данных.");
        return -1;
    }

    $.ajax({
        type: 'post',
        url: 'edit',
        data: {'title': title, 'id': id},
        dataType: 'json',
        success: function (data) {
            exception(data);
        },
        error: function (rsp) {
            var error = JSON.parse(rsp.responseText);
            exception(error);
        }
    });
};

var titleMatches = function (title) {
    var regexMark = new RegExp(/^[A-Za-zА-Яа-я ()-]{3,50}$/);
    if (title.search(regexMark) == -1){
        return false;
    }
    else{
        return true;
    }
};

var deleteCategory = function (id) {
    $.ajax({
        type: 'post',
        url: 'delete',
        data: {'id': id},
        dataType: 'json',
        success: function (data) {
            exception(data);
            location.reload();
        },
        error: function (rsp) {
            var error = JSON.parse(rsp.responseText);
            exception(error);
        }
    });
};

var selectAll = function () {
    var result = $("#result");
    if (result != null){
        result.remove();
    }
    
    $.ajax({
        type: 'get',
        url: 'search',
        dataType: 'json',
        success: function (data) {
            if(data == null || data.length == 0){
                exception("Не найдены категории");
                return -1;
            }
            print(data);
        },
        error: function (rsp) {
            var error = JSON.parse(rsp.responseText);
            exception(error);
        }
    });
};

var print = function (categories) {
    var table = $('<table id = "result"></table>').addClass('table_blur');
    var row = $('<th>Название категории</th><th></th>');
    table.append(row);

    for (var j = 0; j < categories.length; j++) {
        var category = categories[j];
        var position = "position" + j.toString();
        row = $('<tr></tr>');
        var rowData = $('<td><input type="text" id="' + position +  '" placeholder="Категория медпрепаратов (3-50 символов и чисел)" pattern="^[A-Za-zА-Яа-я ()-]{3,50}$" value="' + category['title'] +
            '" onchange="changes(' + position + ', ' + category['id'] + ')"></td><th><input type = "button" value="-" onclick="deleteCategory(' + category['id'] + ')"></th>'); 
        row.append(rowData);
        table.append(row);
    }
    row = $('<tr></tr>');
    var rowData = $('<td><input type="text" id="title" placeholder="Категория медпрепаратов (3-50 символов и чисел)" pattern="^[A-Za-zА-Яа-я ()-]{3,50}$"></td><th><input type = "button" value="+" onclick="added()"></th>');
    row.append(rowData);
    table.append(row);
    $('#categories').append(table);
};



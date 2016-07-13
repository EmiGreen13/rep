var added = function () {
    var title = $("#title").val();
    var manufacture = $("#manufacture").val();
    var country = $("#country").val();
    var price = $("#price").val();
    var description = $("#description").val();

    $.ajax({
        type: 'post',
        url: 'add',
        data: {'title': title, 'categoryId': categoryId, 'manufacture': manufacture, 'country': country, 'price': price, 'description': description},
        dataType: 'json',
        success: function (data) {
            exception(data);
            clear();
        },
        error: function (rsp) {
            var error = JSON.parse(rsp.responseText);
            exception(error);
        }
    });

};

var clear = function () {
    $("#title").val("");
    $("#manufacture").val("");
    $("#country").val("");
    $("#price").val("");
    $("#description").val("");
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

var search = function () {
    var searchTitle = $("#searchTitle").val();
    $.ajax({
        type: 'get',
        url: 'search',
        data: {'param': searchTitle, 'categoryId': categoryId},
        dataType: 'json',
        success: function (data) {

            var elem = $("#drugs");
            if (elem != null){
                elem.remove();
            }

            if (data.length == 0){
                exception("Препараты не найдены");
                return -1;
            }            
            printDrugs(data);
        },
        error: function (rsp) {
            var error = JSON.parse(rsp.responseText);
            exception(error);
        }
    });
};

var titleMatches = function (title) {
    var regexMark = new RegExp(/^[A-Za-zА-Яа-яЁё ]{3,50}$/);
    if (title.search(regexMark) == -1){
        return false;
    }
    else{
        return true;
    }
};

var deleteDrug = function (id) {
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
        url: '../categories/search',
        dataType: 'json',
        success: function (data) {
            if(data == null || data.length == 0){
                exception("Не найдены категории");
                return -1;
            }
            printCategories(data);
        },
        error: function (rsp) {
            var error = JSON.parse(rsp.responseText);
            exception(error);
        }
    });
};

var printDrugs = function (drugs) {
    var table = $('<table id = "drugs"></table>').addClass('table_blur');
    var row = $('<th>Название</th><th>Производитель</th><th>Страна</th><th>Цен(а</th><th></th>');
    table.append(row);

    for (var j = 0; j < drugs.length; j++) {
        var drug = drugs[j];
        row = $('<tr></tr>');
        var rowData = $('<td>' + drug['title'] + '</td><td>' + drug['manufacture'] + '</td><td>' + drug['country'] + '</td><td>' + drug['country'] + '</td><td>' + 
        '<input type="button" value="-" onclick="deleteDrug(' + drug['id'] + ')">');
        row.append(rowData);
        table.append(row);
    }

    $('#out').append(table);
};


var addForm = function () {
    var table = $('<table id = "result"></table>').addClass('table_blur');
    var row = $('<th>Добавить препарат</th>');
    table.append(row);
    table.append('<tr><td><input required type="text" id="title" placeholder="Название препарата (3-50 символов и чисел)" pattern="^[A-Za-zА-Яа-яЁё0-9 ()-]{3,50}$"></td></tr>');
    table.append('<tr><td><input required type="text" id="manufacture" placeholder="Производитель (3-50 символов и чисел)" pattern="^[A-Za-zА-Яа-яЁё0-9 ()-]{3,50}$"></td></tr>');
    table.append('<tr><td><input required type="text" id="country" placeholder="Страна (3-50 символов и чисел)" pattern="^[A-Za-zА-Яа-яЁё0-9 ()-]{3,50}$"></td></tr>');
    table.append('<tr><td><input required type="text" id="price" placeholder="Стоимость"></td></tr>');
    table.append('<tr><td><input type="text" id="description" placeholder="Описание (3-50 символов и чисел)"></td></tr>');
    table.append('<tr><td><input type = "button" value="Добавить" onclick="added()"></td></tr>');
    $('#categories').append(table);


    var table2 = $('<table id = "search"></table>').addClass('table_blur');
    var row = $('<th>Поиск препарата</th>');
    table2.append(row);
    table2.append('<tr><td><input required type="text" id="searchTitle" placeholder="Название или номер препарата" pattern="^[A-Za-zА-Яа-яЁё0-9 ()-]{1,50}$"></td></tr>');
    table2.append('<tr><td><input type = "button" value="Поиск" onclick="search()"></td></tr>');
    $('#search').append(table2);
};

var printCategories = function (categories) {
    var table = $('<table id = "result"></table>').addClass('table_blur');
    var row = $('<th>Название категории</th><th></th>');
    table.append(row);

    for (var j = 0; j < categories.length; j++) {
        var category = categories[j];
        row = $('<tr></tr>');
        var rowData = $('<td><a href = "select?id=' + category['id'] + '">' + category['title'] + '</a></th>');
        row.append(rowData);
        table.append(row);
    }

    $('#categories').append(table);
};



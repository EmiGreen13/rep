var add = function (id) {

    $.ajax({
        type: 'post',
        url: 'select/order',
        data: {'drug': id},
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

var printOrders = function (orders) {

    if (orders.length == 0){
        exception("Заказы не найдены");
        return -1;
    }


    var table = $('<table id = "drugs"></table>').addClass('table_blur');
    var row = $('<th>Название товара</th><th>Производитель</th><th>Стоимость</th><th></th>');
    table.append(row);

    alert("sdf")

    for (var j = 0; j < orders.length; j++) {
        var order = orders[j];
        var drug = order['drug'];
        row = $('<tr></tr>');
        var rowData = $('<td>' + drug['title'] + '</td><td>' + drug['manufacture'] + '</td><td>' + drug['price'] + '</td><td>' +
            '<input type="button" value=" Удалить" onclick="deleteOrders(' + order['id'] + ')"></td>');
        row.append(rowData);
        table.append(row);
    }
    $('#out').append(table);
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

var search = function () {
    var searchTitle = $("#searchTitle").val();
    $.ajax({
        type: 'get',
        url: 'getdrugs',
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

var deleteOrders = function (id) {
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
        url: 'getcategories',
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
        '<input type="button" value="В корзину" onclick="add(' + drug['id'] + ')">');
        row.append(rowData);
        table.append(row);
    }
    $('#out').append(table);
};


var addForm = function () {
    var table2 = $('<table id = "search"></table>').addClass('table_blur');
    var row = $('<th>Поиск препарата</th>');
    table2.append(row);
    table2.append('<tr><td><input required type="text" id="searchTitle" placeholder="Название или номер препарата" pattern="^[A-Za-zА-Яа-яЁё0-9 ()-]{1,50}$"></td></tr>');
    table2.append('<tr><td><input type = "button" value="Поиск" onclick="search()"></td></tr>');
    $('#search').append(table2);
};

var printCategories = function (categories) {
    var table = $('<table id = "result"></table>').addClass('table_blur');
    var row = $('<th>Выберите категорию</th><th></th>');
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



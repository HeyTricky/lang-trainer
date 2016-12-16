//Разделение текста и перевод
function translateText() {
    var text = $("#text-translate").val();
    if (!text) {
        alertMessage("<strong>Error!</strong> Text must not be empty!", "danger");
        return;
    }
    $.ajax({
        method: "POST",
        url: "/rest/words/translate",
        data: text,
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            displayTable(data);
            $("#btns-group").show();
        },
        error: function () {
            alertMessage("<strong>Error of translating!</strong> Check your request!", "danger");
        }
    });
}

//Добавление нового слова
function addWord(saveBtn) {
    var word = {
        english: saveBtn.closest('tr').find('.word-english').text(),
        russian: saveBtn.closest('tr').find('.word-russian').text(),
        countAll: 0,
        countRight: 0
    };
    $.ajax({
        url: "/rest/words/create",
        type: "POST",
        data: JSON.stringify(word),
        contentType: "application/json",
        success: function (word) {
            var respContent = "<strong>Success!</strong> Word ({0}:{1})  was added!".f(word.english, word.russian);
            alertMessage(respContent, 'success')
            saveBtn.hide();
        },
        error: function (response) {
            alertResponse(jQuery.parseJSON(response.responseText));
        }
    });
}

//Изменение ячейки в таблице
function editTd(editTd) {
    var editBtn = editTd.closest('tr').find('.edit-btn')
    if (editBtn.css('display') != 'none') {
        var oldValue = editTd.text();
        var newValue = prompt("Enter new value", oldValue);
        if (newValue != '' && newValue != null && newValue != oldValue) {
            editTd.text(newValue);
        }
    }
}

//Удаление ячейки в таблице
function deleteTd(deleteBtn) {
    deleteBtn.closest('tr').remove();
}

//Отрисовка таблицы
function displayTable(data) {
    var wordsTable = $('#wordsTable');
    wordsTable.empty();
    wordsTable.append("<thead><tr><th width='40%'>English</th><th  width='40%'>Russian</th><th>Actions</th></tr></thead><tbody>");
    for (var i = 0; i < data.length; i++) {
        if (data[i].exists)
            displayExistsWord(data[i]);
        else
            displayNewWord(data[i]);
    }
    wordsTable.append("</tbody>");
}

//Отрисовка нового слова
function displayNewWord(word) {
    $("#wordsTable").append("<tr class='data'><td class='editable-td word-english' style='cursor: pointer;' onclick='editTd($(this))'>{0}</td>".f(word.english) +
        "<td class='editable-td word-russian' style='cursor: pointer;' onclick='editTd($(this))'>{0}</td>".f(word.russian) +
        "<td><a class='btn btn-default edit-btn' onclick='addWord($(this))' )>Save</a> " +
        " <a class='btn btn-default delete-btn' onclick='deleteTd($(this))'>Delete</a></td></tr>");
}

//Отрисовка существующего слова
function displayExistsWord(word) {
    $("#wordsTable").append("<tr class='data'><td class='word-english' onclick='editTd($(this))'>{0}</td>".f(word.english) +
        "<td class='word-russian' style='cursor: pointer;' onclick='editTd($(this))'>{0}</td>".f(word.russian) +
        "<td><a class='btn btn-default edit-btn' onclick='addWord($(this))' style='display: none' )>Save</a> " +
        " <a class='btn btn-default delete-btn' style='margin-left: 62px;' onclick='deleteTd($(this))'>Delete</a></td></tr>");
}

//Добавление всех слов из таблицы
function addAll() {
    var table = $("#wordsTable");
    var words = [];
    $('.data', table).each(function () {
        if ($(this).find(".edit-btn").css('display') != 'none') {
            var word = {
                english: $(this).find('.word-english').text(),
                russian: $(this).find('.word-russian').text(),
                countAll: 0,
                countRight: 0
            };
            words.push(word);
        }
    });
    if (words.length == 0) {
        alertMessage("<strong>Error! </strong>All words were already added!", 'danger');
        return;
    }
    $.ajax({
        url: "/rest/words/add/all",
        type: "POST",
        data: JSON.stringify(words),
        contentType: "application/json",
        success: function (word) {
            $('.data', table).each(function () {
                $(this).find('.edit-btn').hide();
            });
        },
        error: function (response) {
            alertResponse(jQuery.parseJSON(response.responseText));
        }
    });
}

//Создание тренировки с добавленными словами
function startTraining() {
    var table = $("#wordsTable");
    var words = [];
    $('.data', table).each(function () {
        if ($(this).find(".edit-btn").css('display') == 'none') {
            var word = {
                english: $(this).find('.word-english').text(),
                russian: $(this).find('.word-russian').text(),
                countAll: 0,
                countRight: 0
            };
            words.push(word);
        }
    });
    if (words.length == 0) {
        alertMessage("<strong>Error! </strong>You have not added the words!", 'danger');
        return;
    }
    $.ajax({
        url: "/rest/trainings/generate/text",
        type: "POST",
        data: JSON.stringify(words),
        contentType: "application/json",
        success: function (training) {
            document.location.href = "/trainings/" + training.id + "/new/text";
        },
        error: function error() {
            alert("Ошибка!");
        }
    });
}

//Форматирование строки с параметрами
String.prototype.format = String.prototype.f = function () {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g, function (m, n) {
        return args[n] ? args[n] : m;
    });
};
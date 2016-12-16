//Добавление нового слова
function addWord() {
    var word = {
        english: $('#addEnglish').val(),
        russian: $('#addRussian').val(),
        countAll: 0,
        countRight: 0
    };
    if (word.russian == "" || word.english == "") {
        alertMessage("<strong>Error!</strong> Fields must not be empty!", "danger");
        return;
    }
    $.ajax({
        url: "/rest/words/create",
        type: "POST",
        data: JSON.stringify(word),
        contentType: "application/json",
        success: function (word) {
            getWords();
            $('#addEnglish').val("");
            $('#addRussian').val("");
            var respContent = "<strong>Success!</strong> Word ({0}:{1})  was created!".f(word.english, word.russian);
            alertMessage(respContent, 'success');
        },
        error: function (response) {
            alertResponse(jQuery.parseJSON(response.responseText));
        }
    });
}

//Изменение существующего слова
function editWord(editBtn) {
    var wordId = editBtn.data('wordId');
    $.ajax({
        url: "/rest/words/" + wordId,
        type: "PUT",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            english: editBtn.closest('tr').find('.word-english').text(),
            russian: editBtn.closest('tr').find('.word-russian').text()
        }),
        success: function (word) {
            var respContent = "<strong>Success!</strong> Word ({0}:{1})  was updated!".f(word.english, word.russian);
            alertMessage(respContent, 'success')
        },
        error: function (response) {
            alertResponse(jQuery.parseJSON(response.responseText));
        }
    }).done(function () {
        editBtn.hide();
    });
}

//Удаление существующего слова
function deleteWord(deleteBtn) {
    var wordId = deleteBtn.data('wordId');
    $.ajax({
        url: "/rest/words/" + wordId,
        type: "DELETE",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (word) {
            deleteBtn.closest('tr').remove();
            var respContent = "<strong>Success!</strong> Word ({0}:{1})  was deleted!".f(word.english, word.russian);
            alertMessage(respContent, 'success')
        },
        error: function () {
            alertMessage("<strong>Error!</strong>Maybe this word is used in training")
        }
    });
}

//Изменение ячейки в таблице
function editTd(editTd) {
    var oldValue = editTd.text();
    var newValue = prompt("Enter new value", oldValue);
    if (newValue != '' && newValue != null && newValue != oldValue) {
        editTd.text(newValue);
        editTd.closest('tr').find('.edit-btn').show();
    }
}

//Отрисовка таблицы
function displayTable(data) {
    var wordsTable = $('#wordsTable');
    wordsTable.empty();
    wordsTable.append("<thead><tr><th width='35%'>English</th>" +
        "<th width='35%'>Russian</th>" +
        "<th width='10%'  style='text-align: center'>Trainings</th>" +
        "<th style='text-align: center'>Actions</th>" +
        "</tr></thead><tbody>");
    for (var i = 0; i < data.length; i++) {
        var display = "";
        if (data[i].countAll != 0)
            display = "style='display: none;'";
        wordsTable.append("<tr><td class='editable-td word-english' onclick='editTd($(this))' style='cursor: pointer;'>{0}</td>".f(data[i].english) +
            "<td class='editable-td word-russian' onclick='editTd($(this))' style='cursor: pointer;'>{0}</td>".f(data[i].russian) +
            "<td align='center' class='trainings-td'>" + data[i].countAll + "</td>" +
            "<td align='center'><a class='btn btn-default edit-btn' href='#' data-word-id={0} style='display: none' onclick='editWord($(this))'>Save</a>  ".f(data[i].id) +
            "<a href='#' class='btn btn-default delete-btn' data-word-id={0} onclick='deleteWord($(this))' {1}>Delete</a></td></tr>".f(data[i].id, display))
        ;
    }
    wordsTable.append("</tbody>");
}

//Получение всех слов и отображение в таблице
function getWords() {
    $.ajax({
        type: "GET",
        url: "/rest/words",
        dataType: "json",
        cache: true,
        success: function (data) {
            displayTable(data);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            alert(textStatus + ":   " + errorThrown);
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

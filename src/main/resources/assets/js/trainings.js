//Создание тренировки
function generateTraining() {
    var n = $("#count-tests").val();
    $.ajax({
        type: 'GET',
        url: '/rest/trainings/generate/' + n,
        dataType: 'json',
        success: function (training) {
            getWordsByTraining(training);
        },
        error: function error() {
            alert("Ошибка!");
        }
    });
}

//Получение всех слов по тренировке
function getWordsByTraining(training) {
    $.ajax({
        url: "/rest/trainings/get/words",
        type: "POST",
        data: JSON.stringify(training),
        contentType: "application/json",
        success: function (words) {
            displayTest(training.id, words);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            alert(textStatus + ":   " + errorThrown);
        }
    });

}

//Получение всех слов по id тренировки (для тренировки из текста)
function getWordsByIdTraining(id) {
    $.ajax({
        url: "/rest/trainings/" + id + "/get/words",
        type: "GET",
        contentType: "application/json",
        success: function (words) {
            displayTest(id, words);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            alert(textStatus + ":   " + errorThrown);
        }
    });

}

//Отрисовка теста
function displayTest(id, data) {
    $("#testTabs").css('display', 'block');
    var ulTab = $("#ul-tab");
    ulTab.empty();
    ulTab.append("<li class='active disabled'><a href='#0'>0</a></li>");
    for (var i = 0; i < data.length; i++) {
        ulTab.append("<li class='disabled'><a href='#{0}'>{0}</a></li>".f(i + 1));
    }
    var contentTab = $("#test-content");
    contentTab.empty();
    alertMessage("Test started! Good luck!", 'info');
    contentTab.append("<div id='0' class='tab-pane fade in active'><h3>Start Training #<span id='id-training'>{0}</span></h3><p><button class='btn btn-lg btn-default' onclick='switchTab(1)'>Go</button></p></div>".f(id));
    for (var j = 0; j < data.length; j++) {
        contentTab.append("<div id='{0}' class='tab-pane fade'><h3>Word #{0}</h3><p>Translate the word <span class='english'>{1}</span></p></div>".f(j + 1, data[j].english));
        getWrongOptions(data[j].id, j + 1, data[j].russian, data.length);
    }
}

//Получение неправильных вариантов для 1 вопроса
function getWrongOptions(id, tab, rus, max) {
    var tabCur = $("#{0}".f(tab));
    $.ajax({
        type: 'GET',
        url: '/rest/trainings/' + id + "/get/wrong",
        dataType: 'json',
        success: function (data) {
            var options = [];
            options.push(rus);
            for (var i = 0; i < data.length; i++) {
                options.push(data[i].russian)
            }
            options.sort();
            displayOptions(tabCur, tab, options, max);
        },
        error: function error() {
            alert("Ошибка!");
        }
    });
}

//Отображение вариантов
function displayOptions(tabCur, tab, options, max) {
    tabCur.append("<div id='options-block-{0}' class='col-sm-10 col-sm-offset-1'></div>".f(tab));
    var block = $("#options-block-{0}".f(tab));
    for (var i = 0; i < options.length; i++) {
        block.append("<button class='btn btn-lg btn-default' style='width: 200px; margin-right: 5px; margin-bottom: 5px;' onclick='checkAnswer($(this), {1}, {2})'>{0}</button>".f(options[i], tab, max));
    }
}

//Проверить ответ
function checkAnswer(btnCur, tab, max) {
    var english = btnCur.closest(".tab-pane").find(".english").text();
    $.ajax({
        url: "/rest/trainings/get/answer",
        type: "POST",
        contentType: 'text/plain',
        data: english,
        success: function (russian) {
            if (btnCur.text() == russian) {
                alertMessage("Yeah! You are right!", 'success');
                saveResult(russian, tab, max);
            }
            else {
                switchTab(tab + 1, max);
                alertMessage("Oops! You are wrong!", 'danger');
            }
        },
        error: function (jqXhr, textStatus, errorThrown) {
            alert(textStatus + ":   " + errorThrown);
        }
    })
}

//Переключение по тестам (табам)
function switchTab(next, max) {
    if (next > max) {
        $("#testTabs").find("li:eq(0) a").tab('show');
        displayResult();
    }
    else
        $("#testTabs li:eq({0}) a".f(next)).tab('show');
}

//Сохранение в базу правильного результата (для статистики)
function saveResult(russian, tab, max) {
    var idTraining = $("#id-training").text();
    $.ajax({
        url: "/rest/trainings/" + idTraining + "/save",
        type: "POST",
        data: russian,
        contentType: "text/plain",
        success: function () {
            switchTab(tab + 1, max);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            alert(textStatus + ":   " + errorThrown);
        }
    });
}

//Отображение результата на первом табе
function displayResult() {
    var tab = $("#0");
    var idTraining = $("#id-training").text();
    $.ajax({
        type: 'GET',
        url: '/rest/trainings/' + idTraining + "/get/result",
        dataType: 'json',
        success: function (words) {
            tab.empty();
            var length = words.length;
            if (words == 0)
                length = 0;
            tab.append("<h3>Your results of training #{0}: ".f(idTraining) + length + " errors</h3>");
            if (length != 0) {
                tab.append("<ul id='list-result' class='list-group'></ul>");
                for (var i = 0; i < words.length; i++) {
                    $("#list-result").append("<li class='list-group-item'>Question: <strong>{0}</strong>&nbsp;&nbsp;&nbsp;&nbsp;Answer: <strong>{1}</strong></li>".f(words[i].english, words[i].russian));
                }
            }
            else
                tab.append("Congratulations! You are very cool!");
            alertMessage("Test has been finished!", 'info');
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

<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">
<script type="text/javascript" src="/assets/js/translate.js"></script>
<div id="alertDiv"></div>
<div class="col-xs-12">
    <h1>Interpreter
        <small> translation of text for training</small>
    </h1>
    <textarea rows="10" class="form-control my-text-area"
              id="text-translate"></textarea>
    <button class="btn btn-default btn-translate" onclick="translateText()">Translate</button>
</div>

<div class="col-xs-12">
    <div id="btns-group" class="none-display">
        <hr>
        <button class="btn btn-default btn-width btn-start" id="start" onclick="startTraining()">Start training
        </button>
        <button class="btn btn-default btn-width my-right" id="add-all-btn" onclick="addAll()">Add all
        </button>
        <h1>Result
            <small> add new words and start training</small>
        </h1>
    </div>

    <table class="table table-condenced" id="wordsTable"></table>
</div>

<#include "/partials/footer.ftl">
<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">
<script type="text/javascript" src="/assets/js/words.js"></script>

<script>
    $(document).ready(function () {
        getWords();
    });
</script>
<div id="alertDiv"></div>

<div class="page-header">
    <h1>Your dictionary
        <small>improvement of vocabulary</small>
    </h1>
    <div class="row">
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-addon">English</span>
                <input type='text' class='form-control' id='addEnglish'>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="input-group">
                <span class="input-group-addon">Russian</span>
                <input type='text' class='form-control' id='addRussian'>
            </div>
        </div>
        <div class="col-sm-2">
            <input type="submit" class='btn btn-default save-btn' id='submit' value="Add" onclick="addWord()">
        </div>
    </div>
</div>
<h1></h1>
<div class="row">
    <div class="col-xs-12">
        <table class="table table-condenced" id="wordsTable"></table>
    </div>
</div>

<#include "/partials/footer.ftl">
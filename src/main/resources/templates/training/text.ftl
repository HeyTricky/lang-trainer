<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">
<script type="text/javascript" src="/assets/js/trainings.js"></script>

<script>
    $(document).ready(function () {
        getWordsByIdTraining($("#id").text());
    });
</script>

<div id="id" class="none-display">${idTraining}</div>
<h1>Training
    <small>check yourself by text</small>
</h1>
<div class="container mrg-top-20">
    <div id="testTabs">
        <ul class="nav nav-tabs" id="ul-tab"></ul>
        <div class="col-sm-6 col-sm-offset-3">
            <div id="alertDiv" class="mrg-top-20"></div>
            <div class="tab-content" id="test-content"></div>
        </div>
    </div>
</div>


<#include "/partials/footer.ftl">
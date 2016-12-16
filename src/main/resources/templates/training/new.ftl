<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">
<script type="text/javascript" src="/assets/js/trainings.js"></script>

<h1>Training
    <small>check yourself</small>
</h1>

<div class="container">
    <div class="list-inline input-block">
        <div class="container">
            <div class="col-sm-4 col-md-3 col-lg-3">
                <div class="input-group">
                    <span class="input-group-addon">Number of tests</span>
                    <input type='number' class='form-control' id='count-tests' value="10" min="1" max="20"
                           onkeypress="return false">
                </div>
            </div>
            <div class="col-sm-2">
                <button class='btn btn-default btn-width' id='submit'
                        onclick="generateTraining()">Generate
                </button>
            </div>
        </div>
    </div>
</div>
<div class="container mrg-top-20">
    <div id="testTabs" class="none-display">
        <ul class="nav nav-tabs" id="ul-tab"></ul>
        <div class="col-sm-6 col-sm-offset-3">
            <div id="alertDiv" class="mrg-top-20"></div>
            <div class="tab-content" id="test-content"></div>
        </div>
    </div>
</div>
</div>


<#include "/partials/footer.ftl">
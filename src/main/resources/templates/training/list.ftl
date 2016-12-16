<#-- @ftlroot ".." -->
<#include "/partials/header.ftl">


<h1>Your statistics
    <small>analyze results</small>
</h1>

<div class="row">
    <div class="col-xs-12">
        <table class="table table-striped">
            <tr class="center-tr">
                <th>ID</th>
                <th>All tests</th>
                <th>Right answers</th>
                <th>Errors</th>
                <th>Result</th>
            </tr>
        <#list trainings as training>
            <tr align="center">
                <td>${training.id!}</td>
                <td class="countAll">${training.countAll!}</td>
                <td class="countRight">${training.countRight!}</td>
                <td>${training.countAll-training.countRight!}</td>
                <td class="result">${(training.countRight/training.countAll)*100!}%</td>
            </tr>
        </#list>
        </table>
    </div>
</div>

<#include "/partials/footer.ftl">
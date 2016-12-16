<#include "/part/header.ftl">
<form action="/" method="get">
    <input type="submit" value="На главную">
</form>
<h1>Запомните слова, прежде чем начать тест</h1>
<div class="row">
    <div class="col-xs-12">
        <table id="myTable" class="table table-striped">
            <tr>
                <th>Русский</th>
                <th>Английский</th>
                <th>Приоритет</th>
            </tr>
        <#list pairsForTest as pair>
            <tr>
                <td>${pair.ruWord!}</td>
                <td>${pair.enWord!}</td>
                <td>${pair.priority!}</td>
            </tr>
        </#list>
        </table>
    </div>
    <form action="/pair/startTest" method="get">
        <input type="submit" value="Начать тест!">
    </form>
</div>
<#include "/part/footer.ftl">
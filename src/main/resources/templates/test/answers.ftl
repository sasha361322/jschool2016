<#include "/part/header.ftl">
<form action="/" method="get">
    <input type="submit" value="На главную">
</form>
<form action="/pair/add" method="get">
    <input type="submit" value="Добавить">
</form>

<form action="/pair/test" method="get">
    <input type="submit" value="Запустить тест!">
</form>
<div class="row">
    <div class="col-xs-12">
        <table id="myTable" class="table table-striped">
            <tr>
                <th>Русский</th>
                <th>Английский</th>
                <th>Приоритет</th>
            </tr>
            <tr>
                <td colspan="3">
                    <b>Слова в которых Вы ошиблись. Запоминайте лучше!</b>
                </td>
            </tr>
        <#list answers as pair>
            <tr>
                <td>${pair.ruWord!}</td>
                <td>Правильный ответ - ${pair.enWord!}</td>
                <td>${pair.priority!}</td>
            </tr>
        </#list>
            <tr>
                <td colspan="3">
                    <b>Неправильных ответов ${count}/${all}</b>
                </td>
            </tr>
        </table>
    </div>
</div>
<#include "/part/footer.ftl">
<#include "/part/header.ftl">
<script>
    function deleteWord(id, number) {
        document.getElementById("myTable").deleteRow(number + 1);
        $.ajax({
            method: "DELETE",
            url: "/rest/pair/" + parseInt(id.replace(/\s+/g, ''))
        })
        .done(function () {
        });
    }
</script>
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
            <#list pairs as pair>
                <tr>
                    <td>${pair.ruWord!}</td>
                    <td>${pair.enWord!}</td>
                    <td>${pair.priority!}</td>
                    <td> <button onclick="deleteWord('${pair.id!}', ${pair_index})"> Удалить </button> </td>
                </tr>
            </#list>
        </table>
    </div>
</div>
<#include "/part/footer.ftl">
<#include "/part/header.ftl">
<script>
    function deleteWord(id) {
        document.getElementById("myTable").deleteRow(id + 1);
    }
    function beforeSubmit() {
        var ruList = document.getElementsByClassName("ruInput");
        var enList = document.getElementsByClassName("enInput");
        var ru = [];
        var en = [];
        var pairs = {
            word: []
        };
        for (var i = 0; i < ruList.length; i++){
            ru.push(ruList.item(i).value);
            en.push(enList.item(i).value);
            pairs.word.push({
                "enWord" : enList.item(i).value,
                "ruWord" : ruList.item(i).value
            });
        }
        document.getElementById("hiddenField").value = JSON.stringify(pairs.word);
    }
</script>
<form action="/" method="get">
    <input type="submit" value="На главную">
</form>
<div class="row">
    <div class="col-xs-12">
        <table id="myTable" class="table table-striped">
            <tr>
                <th>Русский</th>
                <th>Английский</th>
            </tr>
            <#list pairs as pair>
                <tr id="${pair_index}">
                    <td><input class="ruInput" id="ruInput${pair_index}" value="${pair.ruWord}" disabled="true"</td>
                    <td><input class="enInput" id="enInput${pair_index}" value="${pair.enWord}"</td>
                    <td> <button onclick="deleteWord(${pair_index})"> Удалить</button> </td>
                </tr>
            </#list>
            <tr>
                <td>
                    <form name="pairs" role="form" action="/pair/addPairs" method="post">
                        <input id="hiddenField" type="text" class="form-control" name="pairs" style="visibility: hidden">
                        <input name="pairs" onclick="beforeSubmit()" type="submit" value="Добавить">

                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>
<#include "/part/footer.ftl">
<#include "/part/header.ftl">
<form action="/" method="get">
    <input type="submit" value="На главную">
</form>
<h1>Ready, Steady, Go!!!!!!!!</h1>
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
                <td><input class="answer" id="input${pair_index}" type="text"></td>
                <td>${pair.priority!}</td>
            </tr>
        </#list>
        </table>
    </div>
    <form name="answers" role="form" action="/pair/checkTest" method="post">
        <input type="submit" value="Проверить!" onclick="beforeSubmit()">
        <input name="answers" id="hiddenField" type="text" style="visibility: hidden">
    </form>
</div>
<script>
    function beforeSubmit() {
        var answerList = document.getElementsByClassName("answer");

        var answers = {
            word: []
        };
        for (var i = 0; i < answerList.length; i++){
            answers.word.push({
                "answer" : answerList.item(i).value
            });
        }
        document.getElementById("hiddenField").value = JSON.stringify(answers.word);
    }
</script>
<#include "/part/footer.ftl">
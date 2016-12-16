<script type="text/javascript" language="javascript">
    function checkfile(sender) {
        var validExts = new Array(".txt");
        var fileExt = sender.value;
        fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
        if (validExts.indexOf(fileExt) < 0) {
            alert("Invalid file selected, valid files are of " +
                    validExts.toString() + " types.");
            document.getElementById("fileSubmit").disabled = true;
            return false;
        }
        else{
            document.getElementById("fileSubmit").disabled = false;
            return true;
        }
    }
</script>
<h1>Или загрузите файл с текстом/ ПОКА НЕ РАБОТАЕТ</h1>
<form name="file" role="form" action="/pair/upload" method="post" enctype="multipart/form-data">
    <input id="file" type="file" name="uploadingFiles" accept=".txt" onchange="checkfile(this)">
    <input type="submit" id="fileSubmit" value="Перевести" disabled>
</form>

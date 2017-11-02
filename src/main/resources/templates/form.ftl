<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>URL Shortener</title>
</head>
<body>
    <form action="/">
        TARGET_URL : <input type="text" name="url"/> <input type="submit"/><br/><br/>
        SHORTENED_URL : <a href="/${hashValue?default("")}">http://localhost:8080/${hashValue?default("" )}</a>
    </form>
</body>
</html>

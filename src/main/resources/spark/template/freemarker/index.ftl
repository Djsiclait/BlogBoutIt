<!DOCTYPE html>
<html>
<head>
    <title>BlogBoutIt Login</title>
    <link type="text/css" rel="stylesheet" href="css/blogtheme.css" />
</head>
<body>
<div>
    <!-- Header -->
    <div>
        <h1>${message}</h1>
    </div>

    <br>

    <!-- Login Form -->
    <div>
        <fieldset>
            <legend>Login to Start to Blog</legend>
            <form>
                Username: <input type="text" name="username" value="Username" required/>
                <br>
                Password: <input type="password" name="password" required/>
                <br>
                <input type="submit" name="submit" value="Login" />
            </form>
        </fieldset>
    </div>

    <br>

    <div>
        <img src="img/write.jpg" alt="Image Not Found" />
    </div>

    <!-- Footer -->
    <div>
        <h3>Blog all day, Blog all night!</h3>
    </div>
</div>
</body>
</html>
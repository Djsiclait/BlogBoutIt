<!DOCTYPE html>
<html>
    <head>
        <title>BlogBoutIt Login</title>
        <link type="text/css" rel="stylesheet" href="css/login.css" />
    </head>
    <body style="background-image: url('img/write.jpg');">
        <!-- Header -->
        <div id="header">
            <h1>${message}</h1>
        </div>

        <div id="container">
            <br>

            <!-- Login Form -->
            <div id="login">
                <fieldset>
                    <legend>Login to Start to Blog</legend>
                    <br>
                    <form>
                        Username: <input type="text" name="username" required/>
                        <br><br>
                        Password: <input type="password" name="password" required/>
                        <br><br>
                        <input id="log" type="submit" name="submit" value="Login" />
                    </form>
                </fieldset>
            </div>

            <br>

            <div>
                <!-- <img src="img/write.jpg" alt="Image Not Found" /> -->
            </div>
        </div>

        <!-- Footer -->
        <div id="footer">
            <h3>Blog all day, Blog all night!</h3>
        </div>
    </body>
</html>
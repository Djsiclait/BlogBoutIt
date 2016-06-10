<#include "/siteHeader.ftl">
<body>

<#--<#include "/navbar.ftl">-->
<!-- Header -->
<div id="header">
    <h1>${message}</h1>
</div>





<div id="container">
    <br>

    <!-- Login Form -->
    <div class="row someform">
        <div class="col s12">
            <div id="login">
                <fieldset>
                    <legend>Login to Start to Blog</legend>
                    <br>
                    <form class="col s12" method="post" action="">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="title" type="text" name="title" class="validate" required/>
                                <label for="title">Title</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <textarea id="textarea1" class="materialize-textarea" name="body"></textarea>
                                <label for="textarea1">Textarea</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input placeholder="(Separate by commas)" id="first_name" type="text" name="tags" class="validate">
                                <label for="first_name">Tags</label>
                            </div>
                        </div>
                        <div class="divider"></div>
                        <input type="hidden" name="user" id="user" value="${userCre.getUsername()}">
                        <div class="row">
                            <div class="col m12">
                                <p class="right-align">
                                    <button class="btn btn-large waves-effect waves-light" type="submit" name="action">
                                        Create Post!
                                    </button>
                                </p>
                            </div>
                        </div>
                    </form>
                </fieldset>
            </div>
        </div>
    </div>
</div>





<!-- Footer -->
<#include "/footer.ftl">


</body>
</html>
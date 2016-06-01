<#include "/siteHeader.ftl">
<body>

<#include "/navbar.ftl">
        <!-- Header -->
        <div id="header">
            <h1>${message}</h1>
        </div>



<div class="row" id="mainContent">
    <div class="col s12 m6">
        <div class="card">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator" src="https://www.visioncritical.com/wp-content/uploads/2014/12/BLG_Andrew-G.-River-Sample_09.13.12.png">
            </div>
            <div class="card-content">
                <span class="card-title activator grey-text text-darken-4">Blog Post Title<i class="material-icons right">comment</i></span>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id </p>
            </div>
            <div class="card-reveal">
                <span class="card-title grey-text text-darken-4">Comments<i class="material-icons right">close</i></span>
                <!--Commentsss-->

                <div class="row" id="comment-container">
                    <div class="col s12 m5">
                        <div class="card-panel teal">
                          <span class="white-text">I am a very simple card. I am good at containing small bits of information.
                          I am convenient because I require little markup to use effectively. I am similar to what is called a panel in other frameworks.
                          </span>
                        </div>
                    </div>

                    <div class="col s12 m5">
                        <div class="card-panel teal">
                          <span class="white-text">I am a very simple card. I am good at containing small bits of information.
                          I am convenient because I require little markup to use effectively. I am similar to what is called a panel in other frameworks.
                          </span>
                        </div>
                    </div>

                    <div class="col s12 m5">
                        <div class="card-panel teal">
                          <span class="white-text">I am a very simple card. I am good at containing small bits of information.
                          I am convenient because I require little markup to use effectively. I am similar to what is called a panel in other frameworks.
                          </span>
                        </div>
                    </div>
                </div>
                <!--End Comments-->
                <div class="row">
                    <form class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <textarea id="textarea1" class="materialize-textarea"></textarea>
                                <label for="textarea1">Textarea</label>
                            </div>
                        </div>
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<br><br>



<!-- Footer -->
<#include "/footer.ftl">


    </body>
</html>
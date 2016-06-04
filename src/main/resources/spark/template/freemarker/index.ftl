<#include "/siteHeader.ftl">
<body>


        <!-- Header -->
<div id="index-banner" class="parallax-container">
<#include "/navbar.ftl">
    <div class="section no-pad-bot">
        <div class="container ed-container">
            <br><br>
            <h1 class="header center orange-text accent-2 text-lighten-2">${message} ${user}</h1>
            <div class="row center">
                <h5 class="header col s12 light">The blog about some of the coolest stuff</h5>
            </div>
            <div class="row center">
                <a href="/register" id="download-button" class="btn-large waves-effect waves-light orange accent-2">Register</a>
            </div>
            <br><br>

        </div>
    </div>
    <div class="parallax"><img src="http://designninjaz.com/wp-content/uploads/2015/01/Slider-BlackDeskScene.jpg" alt="Unsplashed background img 1"></div>
</div>
<!-- End Header -->


<div class="row" id="mainContent">
<#list listaArticulos?reverse as articulo>
    <div class="col s12 m6" id="blog-post">

        <div class="card">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator" src="http://loremflickr.com/800/400">
            </div>
            <div class="card-content">
                <span class="card-title activator grey-text text-darken-4">${articulo.getTitle()}<i class="material-icons right">comment</i></span>
                <p>${articulo.getBody()}</p>
            </div>
            <div class="card-reveal">
                <span class="card-title grey-text text-darken-4">Comments<i class="material-icons right">close</i></span>
                <!--Commentsss-->

                <div class="row" id="comment-container">
                    <#list comments?reverse as comment>
                        <#if comment.getArticle() == articulo.getId()>
                    <div class="col s12 m6">
                        <div class="card-panel teal">
                          <span class="white-text">
                              <h5>${comment.getAuthor()}:<i class="material-icons right">delete</i></h5>
                              <div class="divider"></div><br>
                             ${comment.getComment()}
                          </span>
                        </div>
                    </div>
                        </#if>
                    </#list>
                </div>
                <!--End Comments-->
                <div class="row">
                    <form class="col s12" action="" method="POST" id="commentForm">
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="hidden" name="postID" value="${articulo.getId()}">
                                <textarea type="input" id="textarea1" value="Sample comment" name="thebodyx" class="materialize-textarea"></textarea>
                                <label for="textarea1">Textarea</label>
                            </div>
                        </div>
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="chip">
                Tag
            </div>
            <div class="chip">
                Tag
            </div>
        </div>

    </div>
</#list>
</div>

<br><br>



<!-- Footer -->
<#include "/footer.ftl">


    </body>
</html>
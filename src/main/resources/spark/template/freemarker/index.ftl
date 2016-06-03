<#include "/siteHeader.ftl">
<body>

<#include "/navbar.ftl">
        <!-- Header -->
        <div id="header">
            <h1>${message}</h1>
        </div>



<div class="row" id="mainContent">
    <div class="col s12 m6" id="blog-post">
<#list listaArticulos as articulo>
        <div class="card">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator" src="https://www.visioncritical.com/wp-content/uploads/2014/12/BLG_Andrew-G.-River-Sample_09.13.12.png">
            </div>
            <div class="card-content">
                <span class="card-title activator grey-text text-darken-4">${articulo.getTitle()}<i class="material-icons right">comment</i></span>
                <p>${articulo.getBody()}</p>
            </div>
            <div class="card-reveal">
                <span class="card-title grey-text text-darken-4">Comments<i class="material-icons right">close</i></span>
                <!--Commentsss-->

                <div class="row" id="comment-container">
                    <#list comments as comment>
                        <#if comment.getArticle() == articulo.getId()>
                    <div class="col s12 m6">
                        <div class="card-panel teal">
                          <span class="white-text">
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
                                <input type="hidden" name="postID" value="12345">
                                <textarea id="textarea1" form="commentForm"  name="comment" class="materialize-textarea"></textarea>
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
    </#list>
    </div>
</div>

<br><br>



<!-- Footer -->
<#include "/footer.ftl">


    </body>
</html>
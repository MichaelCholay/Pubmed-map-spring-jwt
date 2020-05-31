var express = require('express');
const apiRouter = express.Router();
const got = require('got');
fs = require('fs');
var convert = require('xml-js');
var app = express();
var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

var myGenericMongoClient = require('./my_generic_mongo_client');

// Replace mongoId by PMID
function replace_mongoId_byPmid(article) {
    article.pmid = article._id;
    delete article._id;
    return article;
}


// Replace mongoId by PMID in ArrayList
function replace_mongoId_byPmid_inArray(publicationArray) {
    for (i in publicationArray) {
        replace_mongoId_byCode(publicationArray[i]);
    }
    return publicationArray;
}

// Find article with publication date after a specific date
function findArticlesWithDateMini(articles, dateMini) {
    var selArticles = [];
    for (i in articles) {
        if (articles[i].date >= dateMini) {
            selArticles.push(articles[i]);
        }
    }
    return selArticles;
}

//exemple URL: http://localhost:9999/article-api/public/article/30926242
apiRouter.route('/article-api/public/article/:pmid')
    .get(function (req, res, next) {
        var articlePmid = req.params.pmid;
        myGenericMongoClient.genericFindOne('articles',
            { '_id': articlePmid },
            function (err, article) {
                res.send(replace_mongoId_byPmid(article));
            });
    });

//exemple URL: http://localhost:9999/article-api/public/article (returning all articles)
//             http://localhost:9999/article-api/public/article?dateMini=2010-01-01
apiRouter.route('/article-api/public/article')
    .get(function (req, res, next) {
        var dateMini = req.query.dateMini;
        myGenericMongoClient.genericFindList('articles', {}, function (err, allArticle) {
            if (dateMini) {
                res.send(replace_mongoId_byPmid_inArray(findArticlesWithDateMini(allArticle, dateMini)));
            } else {
                res.send(replace_mongoId_byPmid_inArray(allArticle));
            }
        });
    });


// Get pmid list for articles with search of pubmed-api
function find_Pmid_bySearch_with_terms(term) {
    var urlApiSearch = 'https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retmode=json&usehistory=y&term=' + term
    let request = new XMLHttpRequest()
    request.open("GET", urlApiSearch)
    request.send()
    request.onload = function () {
        //console.log("requestText :" + request.responseText)
        if (request.status === 200) {
            var responseJs = JSON.parse(request.responseText)
            var querykey = responseJs.esearchresult.querykey
            var webenv = responseJs.esearchresult.webenv
            console.log("querykey: " + querykey)
            console.log("webenv: " + webenv)
            console.log("idlist: " + responseJs.esearchresult.idlist)
            find_Article_Data_byFtech_with_PMID(querykey, webenv)
        }
    }
}

// Get all data of articles with Pubmed api
apiRouter.route('/article-api/public/articlePmidFinder/:term')
    .get(function (req, res, next) {
        var term = req.params.term
        find_Pmid_bySearch_with_terms(term)
    })

// Get all data for articles with fetch of pubmed-api and xml conversion
function find_Article_Data_byFtech_with_PMID (querykey, webenv) {
    var urlApiFetch = 'https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&query_key=' + querykey + '&WebEnv=' + webenv + '&rettype=abstract&retmode=xml'
    let request = new XMLHttpRequest()
    request.open("GET", urlApiFetch)
    // request.responseType = "document"
    request.send()
    request.onload = () => {
        console.log("requestSatus :" + request.status)
        if (request.status === 200) {
        //    bodyJs = xml_to_Js(request.responseText, options)
           responseJs = convert.xml2js(request.responseText, options)
           attributes_for_one_article(responseJs)
        }
    }
}

function attributes_for_one_article (responseJs) {
    var article = new Object()
    article.pmid = responseJs.PubmedArticleSet.PubmedArticle.MedlineCitation.PMID
    article.articleTitle = responseJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.ArticleTitle
    article.journal = responseJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.Journal.Title
    console.log("new article: " + JSON.stringify(article))
}


        
        var publiList = []

        // console.log("***** Find " + publiListInput.length + " articles with this request ******")

        // recuperation des infos de chaque article de la requete
        /*for (i in publiListInput) {
            var article = new Object()
            medlineCitation = publiListInput[i].MedlineCitation
            article.pmid = publiListInput[i].MedlineCitation.PMID
            article.articleTitle = publiListInput[i].MedlineCitation.Article.ArticleTitle
            article.journal = publiListInput[i].MedlineCitation.Article.Journal.Title

            ///////////////////////////////////////////////////////////////////
            console.log("PMID " + publiListInput[i].MedlineCitation.PMID)
            console.log("year " + publiListInput[i].PubmedData.History.PubMedPubDate.Year)
            console.log("mont " + publiListInput[i].PubmedData.History.PubMedPubDate.Month)
            console.log("day " + publiListInput[i].PubmedData.History.PubMedPubDate.Day)
        }
        }*/
    


// Conversion of xml results to Js object
const removeJsonTextAttribute = function (value, parentElement) {
    try {
        const parentOfParent = parentElement._parent;
        const pOpKeys = Object.keys(parentElement._parent);
        const keyNo = pOpKeys.length;
        const keyName = pOpKeys[keyNo - 1];
        const arrOfKey = parentElement._parent[keyName];
        const arrOfKeyLen = arrOfKey.length;
        if (arrOfKeyLen > 0) {
            const arr = arrOfKey;
            const arrIndex = arrOfKey.length - 1;
            arr[arrIndex] = value;
        } else {
            parentElement._parent[keyName] = value;
        }
    } catch (e) { }
};

var options = {
    compact: true,
    spaces: 2,
    trim: true,
    nativeType: false,
    ignoreDeclaration: true,
    ignoreInstruction: true,
    ignoreAttributes: true,
    ignoreComment: true,
    ignoreCdata: true,
    ignoreDoctype: true,
    textFn: removeJsonTextAttribute
};

// function xml_to_Js (responseText, options) {
//     convert.xml2js(responseText, options);
// }


exports.apiRouter = apiRouter;


///////////////////////////////////////////////////////////////////////////////////
/*(async () => {
    // Connection à Database
    await mongoose.connect(urlMongo, {
        useCreateIndex: true,
        useNewUrlParser: true,
        useUnifiedTopology: true,
        writeConcern: { w: "majority", wtimeout: 5000 }
    }, function (err, res) {
        if (err) {
            console.log('ERROR connection');
        } else {
            console.log('Success connection');
        }
    })

    //Get pour obtenir PMID des articles recherchés
    try {
        var term = 'hybrigenics'
        const response1 = await got('https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=' + term + '&retmode=json&usehistory=y', { responseType: "json" })
        var querykey = response1.body.esearchresult.querykey
        var webenv = response1.body.esearchresult.webenv
        console.log("querykey: " + querykey)
        console.log("webenv: " + webenv)
        console.log("idlist: " + response1.body.esearchresult.idlist)
    }
    catch (error) {
        console.log(error);
    }


    -----------A ESSAYER ----------------
let pages = response.query.pages;
Object.keys(pages).forEach(id => {
    let page = pages[id];
    console.log(page.title, page.foo);
});
-----------------------------------------

    // GET pour obtenir les infos des articles à partir des PMID
    //https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=30926242,30926243&rettype=abstract&retmode=xml
    var eutilsUrl = 'https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&query_key=' + querykey + '&WebEnv=' + webenv + '&rettype=abstract&retmode=xml'
    try {
        const response = await got(eutilsUrl);

        // Options pour conversion xml -> Js / Json
        var options = {
            compact: true,
            spaces: 2,
            trim: true,
            nativeType: false,
            ignoreDeclaration: true,
            ignoreInstruction: true,
            ignoreAttributes: true,
            ignoreComment: true,
            ignoreCdata: true,
            ignoreDoctype: true,
            textFn: removeJsonTextAttribute
        };
        var bodyJson = convert.xml2json(response.body, options);
        var bodyJs = convert.xml2js(response.body, options);

        // Ecriture fichier JSON
        fs.writeFile('test2.json', bodyJson, function (err) {
            if (err) return console.log(err);
            console.log('****** fichier json créé ******');
        });


        var publiListInput = bodyJs.PubmedArticleSet.PubmedArticle
        var publiList = []

        console.log("***** Find " + publiListInput.length + " articles with this request ******")

        // recuperation des infos de chaque article de la requete
        for (var x = 0; x < publiListInput.length; x++) {
            var article = new Object()
            medlineCitation = publiListInput[x].MedlineCitation
            article.pmid = publiListInput[x].MedlineCitation.PMID
            article.articleTitle = publiListInput[x].MedlineCitation.Article.ArticleTitle
            article.journal = publiListInput[x].MedlineCitation.Article.Journal.Title

            ///////////////////////////////////////////////////////////////////
            console.log("year " + publiListInput[x].PubmedData.History.PubMedPubDate.Year)
            console.log("mont " + publiListInput[x].PubmedData.History.PubMedPubDate.Month)
            console.log("day " + publiListInput[x].PubmedData.History.PubMedPubDate.Day)
            var optionDate = { year: "numeric", month: "2-digit", day: "2-digit" }
            date = new Date(Date.UTC(publiListInput[x].MedlineCitation.DateCompleted.Year, publiListInput[x].MedlineCitation.DateCompleted.Month - 1, publiListInput[x].MedlineCitation.DateCompleted.Day))
            article.publicationDate = date.toLocaleDateString(undefined, optionDate)
            // if (medlineCitation.hasOwnProperty("ArticleDate")){
            //     article.epubDate = moment(publiListInput[x].MedlineCitation.Article.ArticleDate.Year, publiListInput[x].MedlineCitation.Article.ArticleDate.Month - 1, publiListInput[x].MedlineCitation.Article.ArticleDate.Day, "MM-DD-YYYY")
            // } else article.epubDate = "Not available",
            article.abstract = publiListInput[x].MedlineCitation.Article.Abstract.AbstractText
            article.pubmedURL = "Pubmed URL: https://pubmed.ncbi.nlm.nih.gov/" + article.pmid
            if (medlineCitation.hasOwnProperty("KeywordList")) {
                article.keywordsList = publiListInput[x].MedlineCitation.KeywordList.Keyword
            } else article.keywordsList = "Not available"
            authorsList = []
            //article.pmid = publiList[x].MedlineCitation.PMID
            console.log()
            console.log("------------------- ARTICLE " + (x + 1) + " / " + publiListInput.length + " -------------------")
            console.log()
            console.log("PMID: " + article.pmid)
            //article.articleTitle = publiList[x].MedlineCitation.Article.ArticleTitle
            console.log("ArticleTitle: " + article.articleTitle)
            console.log("Journal: " + article.journal)
            console.log("publicationDate: " + article.publicationDate)//.format("YYYY-MM-DD"))
            // console.log("epubDate: " + article.epubDate)
            console.log("abstract: " + article.abstract)
            console.log("pubmedURL: " + article.pubmedURL)
            console.log("keywordsList: " + JSON.stringify(article.keywordsList, null, " "))

            // recuperation des infos de chaque auteur pour chaque article de la requete
            var authorsListInput = publiListInput[x].MedlineCitation.Article.AuthorList.Author
            for (var y = 0; y <= authorsListInput.length - 1; y++) {
                var author = new Object()
                author.lastName = authorsListInput[y].LastName
                author.foreName = authorsListInput[y].ForeName
                author.AffiliationInfo = authorsListInput[y].AffiliationInfo
                var affiliationInfoString = JSON.stringify(authorsListInput[y].AffiliationInfo)
                // console.log(typeof affiliationInfoString)
                //var affiliationParsed= JSON.parse(affiliationInfoString)
                // if (author.AffiliationInfo == undefined){
                //     return "Undefined value !"
                // } else if (affiliationInfoString.includes("Affiliation")){
                //     var affiliationAndEmail = authorsListInput[y].AffiliationInfo.Affiliation
                //     if (affiliationAndEmail.includes("Electronic address:")){
                //         console.log("Affiliation and Email: " + affiliationAndEmail)
                //         var affiliation = affiliationAndEmail.split('. Electronic address: ')
                //         author.affiliation = affiliation[0]
                //         console.log("Affiliation: " + author.affiliation)
                //         // console.log("type: " + typeof affiliation[1])
                //         if (affiliation[1].slice(-1) === '.') {
                //             author.email = affiliation[1].slice(0, affiliation[1].length - 1)
                //         } else author.email = affiliation[1]
                //     } else author.affiliation = authorsListInput[y].AffiliationInfo.Affiliation
                // } else author.affiliation = "Not published"
                console.log("affiliationInfo: " + affiliationInfoString)
                console.log("Affiliation: " + author.affiliation)
                // console.log(author.email)
                authorsList.push(author)

                // console.log("author: " + JSON.stringify(author))
            }
            // console.log("Authors: " + publiList[x].MedlineCitation.Article.AuthorList.Author)
            console.log("***** Find " + authorsList.length + " authors for this article *****")
            console.log("authors: " + JSON.stringify(authorsList, null, " "))

            publiList.push(article)

            // mapping des articles avec MongoDb
            var articleMongo = mongoose.model('Publication', publiSchema);

            var articleMongo = new articleMongo()
            articleMongo.pmid = article.pmid
            articleMongo.articleTitle = article.articleTitle
            articleMongo.journal = article.journal
            articleMongo.publicationDate = article.publicationDate
            // articleMongo.epubDate = article.epubDate
            articleMongo.abstract = article.abstract
            articleMongo.pubmedURL = article.pubmedURL
            articleMongo.keywordsList = article.keywordsList
            articleMongo.authorsList = authorsList

            articleMongo.save({ writeConcern: { w: "majority", wtimeout: 5000 } }, function (err) {
                if (err) throw err
                console.log("Documents with PMID: " + articleMongo.pmid + " is inserted with success ")
            })
        }



        // for (var z = 0; z < publiList.length; z++) {
        //     var article = mongoose.model('Publication', publiSchema);
        //     var article = new article()
        //     article.pmid = publiListInput[z].pmid
        //     // article.articleTitle = articleTitle
        //     // article.journal = journal
        //     // article.publicationDate = publicationDate
        //     // article.epubDate = epubDate
        //     // article.abstract = abstract
        //     // article.pubmedURL = pubmedURL
        //     // article.keywordsList = keywordsList
        //     // article.authorsList = authorsList

        //     article.save({ writeConcern: { w: "majority", wtimeout: 5000 } }, function (err) {
        //         if (err) throw err
        //         console.log("Number of documents inserted: ")

        //         // db.close();
        //     })
        // };

        // publiList.save(function(err){
        //     if(err){
        //       res.send(err);
        //     }
        //     res.send({message : 'Bravo, la publication est maintenant stockée en base de données'});
        //   })

        // // const assert = require('assert');
        //
        // // const dbName = 'Publication';

        // MongoClient.connect(url, { useUnifiedTopology: true }, function (err, db) {
        //     if (err) throw err
        //     var dbo = db.db("Publication");
        //     var myobj = publiList
        //     dbo.collection("pubmedId").insertMany(myobj, { writeConcern: { w: "majority" , wtimeout: 5000 } }, function (err, res) {
        //         if (err) throw err
        //         console.log("Number of documents inserted: " + res.insertedCount)
        //         db.close();
        //     });
        // });
        // authorsList.forEach(function(author, index, array){
        //     console.log(index, author.lastName)
        // })



        //console.log("PMID: " + bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.PMID);
        // var title = bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.ArticleTitle
        // console.log("ArticleTitle: " + title);

        // var authorsList = bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.AuthorList.Author
        // console.log(authorsList)
        //console.log("LastAuthor: " + authorsList[authorsList.length-1].LastName+ " " + authorsList[authorsList.length-1].ForeName)
        // var affiliationAndEmail = authorsList[authorsList.length - 1].AffiliationInfo.Affiliation
        // console.log("Affiliation and Email: " + affiliationAndEmail)
        // var affiliation = affiliationAndEmail.split('. Electronic address: ')
        // console.log("Affiliation: " + affiliation[0])
        // if (affiliation[1].slice(-1) === '.') {
        //     console.log("Email: " + affiliation[1].slice(0, affiliation[1].length - 1))
        // } else console.log("Email: " + affiliation[1])

        // console.log("Journal: " + bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.Journal.Title);

        // var publicationDate = new Date(bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.Journal.JournalIssue.PubDate.Year, bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.Journal.JournalIssue.PubDate.Month - 1, bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.Journal.JournalIssue.PubDate.Day).toLocaleDateString();
        // console.log("Publication date: " + publicationDate)

        // var epubDate = new Date(bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.ArticleDate.Year, bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.ArticleDate.Month - 1, bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.ArticleDate.Day).toLocaleDateString()
        // console.log("Epub date: " + epubDate);

        // console.log("Abstract: " + bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.Article.Abstract.AbstractText);

        // console.log("Pubmed URL: https://pubmed.ncbi.nlm.nih.gov/" + bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.PMID);

        // var keywordsList = bodyJs.PubmedArticleSet.PubmedArticle.MedlineCitation.KeywordList
        // console.log(keywordsList)

        // var post = (function (req, res) {
        //     var public = new Public();
        //     public.pmid = pmid;
        //     piscine.title = title;
        //     piscine.save(function (err) {
        //         if (err) {
        //             res.send(err);
        //         }
        //         res.json({ message: 'Bravo, la public est maintenant stockée en base de données' });
        //     });
        // });
    } catch (error) {
        console.log(error);
    }
})();

const removeJsonTextAttribute = function (value, parentElement) {
    try {
        const parentOfParent = parentElement._parent;
        const pOpKeys = Object.keys(parentElement._parent);
        const keyNo = pOpKeys.length;
        const keyName = pOpKeys[keyNo - 1];
        const arrOfKey = parentElement._parent[keyName];
        const arrOfKeyLen = arrOfKey.length;
        if (arrOfKeyLen > 0) {
            const arr = arrOfKey;
            const arrIndex = arrOfKey.length - 1;
            arr[arrIndex] = value;
        } else {
            parentElement._parent[keyName] = value;
        }
    } catch (e) { }
};*/


// const insertDocuments = function(db, callback) {
//     // Get the documents collection
//     const collection = db.collection('Publication');
//     // Insert some documents
//     collection.insertMany(body.js, function(err, result) {
//       assert.equal(err, null);
//       assert.equal(result.result.n);
//       assert.equal(result.ops.length);
//       console.log("Inserted documents into the collection");
//       callback(result);
//     });}





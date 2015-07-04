 function ajax(param)
        {
            var xmlhttp;
            var link = "./gb";
            if(param != null) {
                link += "?page=" + param;
            }
            if (window.XMLHttpRequest)
            {
                xmlhttp=new XMLHttpRequest();
            }
            else
            {
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }

            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                    document.getElementById("guest").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("GET",link,true);
            xmlhttp.send();
        }

        function send() {
            var xmlhttp1;
            var sendlink = "./gb";
            if (window.XMLHttpRequest)
            {
                xmlhttp1=new XMLHttpRequest();
            }
            else
            {
                xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
            }

            xmlhttp1.onreadystatechange=function()
            {
                if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
                {
                    ajax('0&new=1');
                }
            }

            var username = document.getElementById('name-field').value;
            var text = document.getElementById('mess-field').value;

            var isEmpty = false;
            var tagsNorm = true;
            var tags = ['b', 'i', 'img'];

            if(username == "" || text == "") {
                isEmpty = true;
            }

            for(var tag in tags) {
                if (!isReplaceable(text, tags[tag])) {
                    tagsNorm = false;
                    var wrongTag = tags[tag];
                    break;
                }
            }

            if(tagsNorm && !isEmpty) {
                document.getElementById('name-field').value = "";
                document.getElementById('mess-field').value = ""
                var params = 'username=' + encodeURIComponent(username) + '&mess=' + encodeURIComponent(text)
                xmlhttp1.open("POST", sendlink, true)
                xmlhttp1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
                xmlhttp1.send(params)
            } else {
                if (isEmpty) {
                    alert('Поля Имя и Сообщение должны быть заполнены!');
                } else if (!tagsNorm) {
                    alert('У вас неправильно расставлены тэги [' + wrongTag + '][/' + wrongTag + ']');
                }
            }
        }

 function isReplaceable(text, tag) {
     var i = 0;
     var norm = true;
     var countOpens = 0;
     var countCloses = 0;

     if((text.indexOf("[" + tag + "]") > text.indexOf("[/" + tag + "]")) || text.lastIndexOf("[/" + tag + "]") < text.lastIndexOf("[" + tag + "]")) {
         return false;
     }

     while(i < text.length) {
         i = text.substring(i).indexOf("[" + tag + "]");
         if (i == -1) {
             break;
         }
         countOpens++;
         i++;
     }

     i = 0;

     while(i < text.length) {
         i = text.substring(i).indexOf("[/" + tag + "]");
         if(i == -1) {
             break;
         }
         countCloses++;
         i++;
     }

     if(countCloses != countOpens)
         norm = false;

     return norm;
 }
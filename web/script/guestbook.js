 function ajax(param)
        {
            var xmlhttp;
            var link = "guestbook.jsp";
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
            var sendlink = "./gb/";
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
                    ajax(1);
                }
            }

            var username = document.getElementById('name-field').value;
            var text = document.getElementById('mess-field').value;
            document.getElementById('name-field').value="";
            document.getElementById('mess-field').value=""
            var params = 'username=' + encodeURIComponent(username) + '&mess=' + encodeURIComponent(text)
            xmlhttp1.open("POST", sendlink, true)
            xmlhttp1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
            xmlhttp1.send(params)
        }
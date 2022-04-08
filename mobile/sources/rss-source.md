# Simple RSS Read

## Goal

Build an iOS app to let users read rss feeds.

We expect to receive:

* The code of the application

* A log with the important decisions and the process to develop the app (we may use the git commit comments for this)

In the next section we list the stories that need to be implemented in the application with the related API endpoints for each story.

Any question please, don't hesitate to ask the person who sent you the exercise.

## Stories

### As a User I want to register and login

```
POST http://167.99.162.146/register
Content-Type: application/json

{"user": "cacciaresi", "password": "cacciaresi"}
> {% client.global.set("auth_token", response.body.access_token); %}

{
  "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMywidXNlcm5hbWUiOiJjYWNjaWFyZXNpIiwiZXhwIjoxNTM3NTA5MDMwLCJlbWFpbCI6IiJ9.k6xKX8Isd8Gh6VbXmIiSln5SnS6N7rCT53OzZkUrZJE"
}
```

```
POST http://167.99.162.146/login
Content-Type: application/json

{"user": "cacciaresi", "password": "cacciaresi"}
> {% client.global.set("auth_token", response.body.access_token); %}

{
  "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMywidXNlcm5hbWUiOiJjYWNjaWFyZXNpIiwiZXhwIjoxNTM3NTA5MDMwLCJlbWFpbCI6IiJ9.k6xKX8Isd8Gh6VbXmIiSln5SnS6N7rCT53OzZkUrZJE", "user_id": 16
}
```

### As a User I want to subscribe to a RSS feed

```
POST http://167.99.162.146/feeds/add
Authorization: Bearer {{auth_token}}
Content-Type: application/json

{"url": "http://contenidos.lanacion.com.ar/herramientas/rss/origen=2"}
```

```
HTTP/1.1 201 Created
Date: Fri, 21 Sep 2018 03:51:48 GMT
Server: WSGIServer/0.2 CPython/3.6.3
Content-Type: application/json
Vary: Accept
Allow: OPTIONS, POST
X-Frame-Options: SAMEORIGIN
Content-Length: 120

{
  "id": 5,
  "title": "Política - lanacion.com",
  "url": "http://contenidos.lanacion.com.ar/herramientas/rss-origen=2"
}
```

### As a User I want to read the articles of my RSS feeds

```
GET http://167.99.162.146/feeds
Authorization: Bearer {{auth_token}}
Content-Type: application/json
```

```
GET http://167.99.162.146/feeds

HTTP/1.1 200 OK
Date: Fri, 21 Sep 2018 03:52:12 GMT
Server: WSGIServer/0.2 CPython/3.6.3
Content-Type: application/json
Vary: Accept
Allow: OPTIONS, GET
X-Frame-Options: SAMEORIGIN
Content-Length: 122

[
  {
    "id": 5,
    "title": "Política - lanacion.com",
    "url": "http://contenidos.lanacion.com.ar/herramientas/rss-origen=2"
  }
]

Response code: 200 (OK); Time: 49ms; Content length: 117 bytes
```


```
GET http://167.99.162.146/feeds/5/articles
Authorization: Bearer {{auth_token}}
Content-Type: application/json
```

```
GET http://167.99.162.146/feeds/5/articles

HTTP/1.1 200 OK
Date: Fri, 21 Sep 2018 03:56:27 GMT
Server: WSGIServer/0.2 CPython/3.6.3
Content-Type: application/json
Vary: Accept
Allow: GET, OPTIONS
X-Frame-Options: SAMEORIGIN
Content-Length: 3349

{
  "status": "200",
  "articles": [
    {
      "id": 148,
      "title": "El día que Néstor Kirchner expresó su rechazo a los fueros parlamentarios",
      "url": "http://www.lanacion.com.ar/2174110-el-dia-nestor-kirchner-expreso-su-rechazo",
      "summary": "",
      "content": null,
      "date": "2018-09-21T00:33:00Z",
      "read_by": [],
      "loaded": "2018-09-21T01:51:33.688710Z"
    },
    {
      "id": 149,
      "title": "Presupuesto 2019: quiénes ganan y quiénes pierden con los cambios en el proyecto",
      "url": "http://www.lanacion.com.ar/2174042-ganadores-perdedores-cambios-presupuesto",
      "summary": "",
      "content": null,
      "date": "2018-09-21T00:11:00Z",
      "read_by": [],
      "loaded": "2018-09-21T01:51:33.701581Z"
    }
   ]
}
Response code: 200 (OK); Time: 63ms; Content length: 3264 bytes
```

### As a User I want to update/refresh a RSS feed

```
PUT http://167.99.162.146/feeds/5/refresh
Authorization: Bearer {{auth_token}}
Content-Type: application/json
```

```
PUT http://167.99.162.146/feeds/5/refresh

HTTP/1.1 200 OK
Date: Fri, 21 Sep 2018 03:57:06 GMT
Server: WSGIServer/0.2 CPython/3.6.3
Content-Type: application/json
Vary: Accept
Allow: PUT, OPTIONS
X-Frame-Options: SAMEORIGIN
Content-Length: 20

{
  "new_articles": 10
}

Response code: 200 (OK); Time: 207ms; Content length: 20 bytes
```

### As a User I want to delete a RSS feed

```
DELETE http://167.99.162.146/feeds/5
Authorization: Bearer {{auth_token}}
Content-Type: application/json
```

```
DELETE http://167.99.162.146/feeds/5

HTTP/1.1 200 OK
Date: Fri, 21 Sep 2018 04:02:31 GMT
Server: WSGIServer/0.2 CPython/3.6.3
Content-Type: application/json
Vary: Accept
Allow: OPTIONS, DELETE
X-Frame-Options: SAMEORIGIN
Content-Length: 2

{}

Response code: 200 (OK); Time: 31ms; Content length: 2 bytes
```

### As a User I want to mark an article of a RSS feed as read/unread

```
PUT http://167.99.162.146/feeds/5/articles/148/toggle_read
Authorization: Bearer {{auth_token}}
Content-Type: application/json
```

```
PUT http://167.99.162.146/feeds/5/articles/148/toggle_read

HTTP/1.1 200 OK
Date: Fri, 21 Sep 2018 03:57:32 GMT
Server: WSGIServer/0.2 CPython/3.6.3
Content-Type: application/json
Vary: Accept
Allow: PUT, OPTIONS
X-Frame-Options: SAMEORIGIN
Content-Length: 49

{
  "article_id": 148,
  "read_by_current_user": true
}

Response code: 200 (OK); Time: 87ms; Content length: 49 bytes
```
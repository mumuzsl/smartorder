// const apiUrl = 'http://mumu.biz:8888'
// const token = 'token=666666'
const user_status = 1
// const apiUrl = 'http://47.98.147.56:9999'
const apiUrl = 'http://localhost:8080'
const rootUrl = apiUrl

function toLocalUrl(url) {
    window.location.href = url
}

function toUrl(url) {
    window.location.href = apiUrl + url
}

function toError(httpStatus) {
    if (httpStatus === 403) {
        // toUrl('/error/' + httpStatus)
        alert("你没有权限执行该操作!")
    } else if (httpStatus == 401) {
        // toLocalUrl('/login.html')
    }
}


function toUrlAndToken(url) {
    window.location.href = perfectUrl(url)
}

function toDownDocument(id) {
    window.location.href = perfectUrl('/download/' + id)
}

function perfectUrl(url) {
    return apiUrl + url + '?'
}

function joinUrl(data) {
    let d = ''
    for (let key in data) {
        // console.log(key + '=' + obj[key]);
        d += '&' + key + '=' + data[key]
    }
    console.log(d);
    return d
}

function readLocalStorage(key) {
    return localStorage.getItem(key)
}

function clearLocalStorage() {
    localStorage.clear();
}

function saveToken(token) {
    localStorage.setItem('token', token)
}

function readToken() {
    return readLocalStorage('token')
}

function datetimeFormat(longTypeDate) {
    var dateTypeDate = "";
    var date = new Date();
    date.setTime(longTypeDate);
    dateTypeDate += date.getFullYear();   //年    
    dateTypeDate += "-" + date.getMonth(date); //月     
    dateTypeDate += "-" + date.getDay(date);   //日    
    dateTypeDate += " " + date.getHours(date);   //时    
    dateTypeDate += ":" + date.getMinutes(date);     //分  
    dateTypeDate += ":" + date.getSeconds(date);     //分  
    return dateTypeDate;
}

function getValue(d, objs) {
    for (let o of objs) {
        console.log(o);
        if (d[o] != undefined)
            return d[o]
    }
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function backToUrl(url, e) {
    if (e === 'edit') {
        setTimeout(() => {
            window.location.href = url
        }, 500);
    }
}

// 需要token

const token = readToken()

// const token = '666666'

function getApi(url) {
    return axios.get(perfectUrl(url), {
        //请求头配置  
        headers: {token: token}
    })
}

function postApi(url, data) {
    return axios.post(perfectUrl(url),
        //参数列表
        data,
        //请求头配置   
        {
            headers: {
                token: token
            }
        })
}


function getRender(el, url, cols, data) {
    return {
        elem: el,
        // url: apiUrl + url + '?' + token + joinUrl(data),
        url: apiUrl + url + '?',
        // headers: { token: token },
        parseData: function (resp) {
            console.log(resp);
            // resp.content.forEach(element => {
            //     element["S"] = user_status
            // });
            return {
                "code": 0,
                "msg": "",
                "count": resp.totalElements,
                "data": resp.elements
            }
        },
        request: {
            limitName: 'size' //每页数据量的参数名，默认：limit
        },
        page: true,
        // height: 'full-10',
        cols: [cols]
    }
}

function updateOrInsert(u, data, flag, id) {
    let data1 = {}
    for (let key in data) {
        if (data[key] != undefined && data[key] != "") {
            data1[key] = data[key]
        }
    }
    if (flag) {
        u += 'update'
        data1['id'] = id
    } else {
        u += 'insert'
    }
    console.log('data', data1);

    return axios.post(perfectUrl(u), data1,
        //请求头配置   
        {
            headers: {token: token}
        })
}

// saveToken('666666')
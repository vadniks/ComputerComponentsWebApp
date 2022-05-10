
export const
    gt = 'GET',
    ps = 'POST',
    ndx = '/',
    lgn = '/lgn',
    lgo = '/lgo',
    clr = '/clr',
    adm = '/adm',
    abt = '/abt'

export function request(method, path, callback, csrfHdr, csrfTkn, payload = null) {
    let rq = new XMLHttpRequest()
    rq.onreadystatechange = () => {
        if (rq.readyState !== 4) return

        if (rq.status === 200)
            method === gt ? callback(JSON.parse(rq.response)) : callback()
        else if (rq.status !== 200)
            alert("An error occurred: " + rq.status)
    }
    rq.open(method, path)
    if (payload != null) rq.setRequestHeader('Content-Type', 'application/json')
    if (method === pt) rq.setRequestHeader(csrfHdr, csrfTkn)
    rq.send(payload)
}

export function redir(whr) { window.location.replace(whr) }

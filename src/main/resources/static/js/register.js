import * as G from './global.js'

const cntFtr = document.getElementById('cntFtr'),
    spn = cntFtr.querySelector('span'),
    frm = document.querySelector('form'),
    lgn = document.querySelector('#lgn'),
    pas = document.querySelector('#pas'),
    mts = document.getElementsByTagName('meta'),
    csrfTkn = mts[2].content,
    csrfHdr = mts[4].content

frm.addEventListener('submit', e => {
    e.preventDefault()

    G.request(
        G.ps, G.rgs,
        () => G.redir(G.ndx),
        csrfHdr, csrfTkn,
        JSON.stringify({name: lgn.value, password: pas.value}),
        () => {
            cntFtr.style.backgroundColor = 'red'
            spn.style.visibility = 'visible'
        })
})

import * as G from './global.js'

const cntFtr = document.getElementById('cntFtr'),
    spn = cntFtr.querySelector('span'),
    frm = document.querySelector('form'),
    lgn = document.querySelector('#lgn'),
    pas = document.querySelector('#pas')

frm.addEventListener('submit', e => {
    e.preventDefault()

    G.request(
        G.ps, G.rgs,
        () => G.redir(G.ndx),
        JSON.stringify({name: lgn.value, password: pas.value}),
        () => {
            cntFtr.style.backgroundColor = 'red'
            spn.style.visibility = 'visible'
        })
})

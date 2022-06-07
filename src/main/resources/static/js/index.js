import * as G from './global.js'

function rqs(whr) { G.request(
    G.ps,
    whr,
    () => G.redir(G.ndx)
) }

window.logOut = function logOut() { rqs(G.lgo) }
window.onClear = function onClear() { rqs(G.clr) }

function elm(a) { return document.getElementById(a) }

const sbt = elm('sbt'), fnm = elm('fnm'), lnm = elm('lnm'),
    phn = elm('phn'), adr = elm('adr'), ttl = elm('sbtTtl'),
    ord = elm('sbtOrd'), ovr = elm('overlay'), clb = elm('clb')

function opn(a) {
    sbt.style.display = a ? 'flex' : 'none'
    ovr.style.display = a ? 'flex' : 'none'
}
window.opn = opn

function pld() { return JSON.stringify({
    fnm: fnm.value,
    lnm: lnm.value,
    phn: phn.value,
    adr: adr.value
}) }

function clr() {
    fnm.value = ''
    lnm.value = ''
    phn.value = ''
    adr.value = ''
}

function pp(tx) {
    clr()
    opn(false)

    clb.textContent = tx
    clb.style.display = 'initial'
    setTimeout(() => { clb.style.display = 'none' }, 2000)
}

window.order = () => { G.request(G.ps, '/ord',
    () => pp('Ordered successfully'),
    pld(), () => pp('Order failed')) }

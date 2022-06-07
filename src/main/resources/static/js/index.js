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
    ord = elm('sbtOrd'), ovr = elm('overlay')

function opn(a) {
    sbt.style.display = a ? 'flex' : 'none'
    ovr.style.display = a ? 'flex' : 'none'
}

function pld() {

}

window.order = () => { G.request(G.ps, '/ord', () => {

}, pld(), () => {

}) }

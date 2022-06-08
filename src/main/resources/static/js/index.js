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
    ord = elm('sbtOrd'), ovr = elm('overlay'), msg = elm('sbtMsg'),
    cst = elm('componentCst'), abt = elm('menuBtAbt')

function chk() {
    for (const i of document.getElementsByClassName('ovrwItmDsc')) {
        console.log(i)
        console.log(i.textContent)
        if (i.textContent.toLowerCase() !== 'Not Selected'.toLowerCase())
            return true
    }
    return false
}

function opn(a) {
    if (!chk()) { alert('Select at least one component'); return }

    sbt.style.display = a ? 'flex' : 'none'
    ovr.style.display = a ? 'flex' : 'none'
    ttl.textContent = cst.textContent
}
window.opn = opn

function pld() { return JSON.stringify({
    firstName: fnm.value,
    lastName: lnm.value,
    phone: phn.value,
    address: adr.value
}) }

function clr() {
    fnm.value = ''
    lnm.value = ''
    phn.value = ''
    adr.value = ''
}

function pp(tx, a) {
    clr()

    msg.textContent = tx
    msg.style.visibility = 'visible'
    ord.classList.add('txbt')

    setTimeout(() => {
        msg.style.visibility = 'hidden'
        ord.classList.remove('txbt')
        if (a) opn(false)
    }, 2000)
}

window.order = () => {
    if (fnm.value.length === 0 ||
        lnm.value.length === 0 ||
        phn.value.length === 0 ||
        adr.value.length === 0)
    { pp('Some fields are empty', false)
      return }

    G.request(G.ps, '/ord',
        () => {
            pp('Ordered successfully', true)
            G.redir(G.ndx)
        },
        pld(), () => pp('Order failed', true))
}

window.cnl = () => G.request(G.ps, '/cnl', () => G.redir(G.ndx),
    null, () => alert('Unable to cancel order'))

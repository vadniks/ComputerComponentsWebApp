import * as G from './global.js'

const mts = document.getElementsByTagName('meta'),
    csrfTkn = mts[2].content,
    csrfHdr = mts[4].content

function rqs(whr) { G.request(
    G.ps,
    whr,
    () => G.redir(G.ndx),
    csrfHdr,
    csrfTkn
) }

window.logOut = function logOut() { rqs(G.lgo) }
window.onClear = function onClear() { rqs(G.clr) }

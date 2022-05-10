import * as G from './global.js'

function rqs(whr) { G.request(
    G.ps,
    whr,
    () => G.redir(G.ndx),
    csrfHdr,
    csrfTkn
) }

export function logOut() { rqs(G.lgo) }
export function onClear() { rqs(G.clr) }

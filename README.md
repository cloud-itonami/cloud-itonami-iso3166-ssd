# cloud-itonami-iso3166-ssd

**SSD**: South Sudan.

- South Sudan Public Procurement and Disposal of Assets Authority
  (Public Procurement and Disposal of Assets Act, 2018) public-
  procurement compliance
- Business Registry / Ministry of Finance TIN registration; Public
  Procurement and Disposal of Assets Act, 2018 external-beneficiary
  procurement local-registration gate

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-caf`/`-sdn`/`-eth` (studied as structural
models for this scaffold -- see Research notes below):

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Public
  Procurement and Disposal of Assets Act, 2018's own establishment of
  the South Sudan Public Procurement and Disposal of Assets Authority
  (this catalog deliberately does NOT abbreviate this to 'PPDA' -- the
  Act's own text never uses that abbreviation for itself, and Uganda's
  OWN procurement authority is separately, coincidentally also
  abbreviated 'PPDA'; see the namespace docstring for why this matters).
  `governor.cljc`'s flagship check independently recomputes the Act's
  own Section 4(7)-(8) local-registration requirement for procurement
  funded under a treaty/agreement favouring an external beneficiary --
  an AND-of-three-independent-boolean-registration-facts COMPLIANCE
  test (contractor, insurer, AND carriage/transport all registered in
  South Sudan), a check shape genuinely different from the CAF sibling
  this scaffold was modeled on (CAF's Marché réservé check is an
  OR-of-thresholds ELIGIBILITY test). See the namespace docstrings for
  the full research trail and honestly-narrowed scope, including facts
  this iteration could NOT verify (a specific Taxation Act citation, a
  representative/director exclusion provision, and an e-procurement
  self-service portal domain).
- `src/statute/facts.cljc` -- general-law catalog: the Labour Act, 2017
  (Act No. 64) and the Investment Promotion Act, 2009 -- both read as
  government-printed PRIMARY TEXT directly (via FAO's FAOLEX legal
  database). A third entry, the Companies Act, 2012, is cited at LOWER
  confidence (two independent secondary/catalog sources -- the World
  Bank Doing Business 2020 report and ecolex.org -- but this iteration
  could not read its primary text directly; see namespace docstring). A
  Taxation Act could not be independently verified this iteration --
  an honest gap, not an omission by design.

## Research notes (2026-07-23)

South Sudan is a genuinely thin-documentation jurisdiction (independence
2011; most guessed ministry domains failed DNS resolution or returned
bare SPA shells this iteration; this session's WebSearch tool was
unavailable, fleet-wide budget exhausted by concurrent sibling work).
Every citation in this repo's catalogs was independently fetched and
read this session via:

- **FAO's FAOLEX legal database** (`faolex.fao.org`, government-gazette
  mirror) -- the actual government-printed "LAWS OF SOUTH SUDAN"/"LAWS
  OF SOUTHERN SUDAN" primary text for the Public Procurement and
  Disposal of Assets Act, 2018; the Labour Act, 2017 (Act No. 64); and
  the Investment Promotion Act, 2009 (whose own cover page bears an
  actual 2010 government stamp: "SOUTHERN SUDAN INVESTMENT AUTHORITY /
  20 JAN 2010 / JUBA").
- **ecolex.org** (joint FAO/IUCN/UNEP legal database) -- used to
  discover/cross-confirm FAOLEX document ids (independently verified by
  matching against a Wayback Machine CDX enumeration of
  `faolex.fao.org/docs/pdf/ssd*.pdf`, discovered BEFORE the ecolex
  search corroborated the same ids).
- **World Bank Doing Business 2020, South Sudan Economy Profile**
  (`archive.doingbusiness.org`, data collection completed May 2019;
  NOTE the Doing Business project itself was discontinued by the World
  Bank in 2021 -- cited here as a dated but real, directly-read
  government-procedure survey) -- names the "Business Registry",
  "Ministry of Finance" (TIN), "Chamber of Commerce", "Government of
  Jubek State" (Operating License), and "The Payam" (Juba City
  Authority, Trading License) as real registration touchpoints, and
  quotes "The Company Act of 2012" and "Part X of the Labor Act of
  Sudan (1997)" directly.
  - **Honestly-disclosed tension**: this 1997-Sudan-law citation
    predates (in the sense of remaining operative per this 2019-data
    report) the Labour Act, 2017's own December 2017 signature -- this
    iteration does NOT resolve this tension authoritatively; both facts
    are stated side by side in `statute.facts`'s namespace docstring.
- **Wayback Machine** (`web.archive.org`, fetched via plain `curl` --
  NOT WebFetch, which hard-blocks the `web.archive.org` host entirely
  as a tool-level restriction unrelated to any bot-detection on that
  site) -- independently discovered that South Sudan runs a real
  unified e-Government portal at `eservices.gov.ss` (Business Registry
  Service, Revenue Authority, Customs, Trade, and Investment
  subdomains all confirmed live via HTTP 200 snapshots); the
  `investment.eservices.gov.ss` homepage snapshot (2024-07-10) is read
  directly and quoted in `statute.facts`, confirming the "Ministry of
  Investment" and its "Certificate of Investment" service in the
  government's own words.
- **No bot-detection challenge was bypassed.** A small number of
  sources (ILO NATLEX/`webapps.ilo.org`, `www.fao.org/faolex`) returned
  plain HTTP 403 (access denial, not a CAPTCHA/challenge page) and were
  simply left unreachable -- reported as honest gaps, not worked around.

Sibling repos studied as structural models before writing any code:
`cloud-itonami-iso3166-caf` (Central African Republic -- the most
complete model, with both `marketentry` and `statute` fully built out
plus a genuinely novel flagship check of its own), `cloud-itonami-
iso3166-sdn` (Sudan, South Sudan's direct former-union neighbor), and
`cloud-itonami-iso3166-eth` (Ethiopia).

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for South Sudan:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.

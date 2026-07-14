(ns statute.facts
  "General-law compliance catalog for Canada (CAN) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/-fra's `statute.facts`
  (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL laws-lois.justice.gc.ca (Department of
  Justice Canada) URL -- never fabricated. A law not in this table has
  NO spec-basis, full stop; extend `catalog`, do not invent an id/url.
  Title and citation for every entry below were directly WebFetch-
  verified against the live laws-lois.justice.gc.ca page on 2026-07-14
  (rendered cleanly, like the UK's legislation.gov.uk and Germany's
  gesetze-im-internet.de).")

(def catalog
  "iso3 -> vector of statute entries."
  {"CAN"
   [{:statute/id "can.business-corporations-act"
     :statute/title "Canada Business Corporations Act"
     :statute/jurisdiction "CAN"
     :statute/kind :law
     :statute/law-number "R.S.C., 1985, c. C-44"
     :statute/url "https://laws-lois.justice.gc.ca/eng/acts/C-44/index.html"
     :statute/url-provenance :official-justice-laws
     :statute/retrieved-at "2026-07-14"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "can.pipeda"
     :statute/title "Personal Information Protection and Electronic Documents Act (PIPEDA)"
     :statute/jurisdiction "CAN"
     :statute/kind :law
     :statute/law-number "S.C. 2000, c. 5"
     :statute/url "https://laws-lois.justice.gc.ca/eng/acts/p-8.6/"
     :statute/url-provenance :official-justice-laws
     :statute/enacted-date "2000"
     :statute/retrieved-at "2026-07-14"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "can.labour-code"
     :statute/title "Canada Labour Code"
     :statute/jurisdiction "CAN"
     :statute/kind :law
     :statute/law-number "R.S.C., 1985, c. L-2"
     :statute/url "https://laws-lois.justice.gc.ca/eng/acts/l-2/"
     :statute/url-provenance :official-justice-laws
     :statute/retrieved-at "2026-07-14"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-can statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "CAN")) " CAN statutes seeded with an "
                 "official laws-lois.justice.gc.ca citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))

(ns culture.facts
  "Country-level regional-culture catalog for South Sudan (SSD) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  South Sudan is thinly documented on the sources this catalog is allowed
  to cite (Wikipedia English); this catalog reflects only what was
  actually verified there rather than padding to a target count. A
  candidate craft entry (Dinka beaded collar) was researched and dropped:
  the only source found (`Culture of South Sudan`) carries just an image
  caption with no substantive description to summarize honestly.

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SSD"
   [{:culture/id "ssd.dish.kisra"
     :culture/name "Kisra"
     :culture/country "SSD"
     :culture/kind :dish
     :culture/summary "Sorghum pancake, listed as a national dish of South Sudan."
     :culture/url "https://en.wikipedia.org/wiki/South_Sudanese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ssd.dish.kajaik"
     :culture/name "Kajaik"
     :culture/country "SSD"
     :culture/kind :dish
     :culture/summary "Fish stew, a South Sudanese dish."
     :culture/url "https://en.wikipedia.org/wiki/South_Sudanese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ssd.dish.combo"
     :culture/name "Combo"
     :culture/country "SSD"
     :culture/kind :dish
     :culture/summary "South Sudanese dish made from spinach, peanut butter and tomatoes."
     :culture/url "https://en.wikipedia.org/wiki/South_Sudanese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ssd.beverage.karkade"
     :culture/name "Karkade"
     :culture/country "SSD"
     :culture/kind :beverage
     :culture/summary "Hibiscus tea, listed among South Sudanese beverages."
     :culture/url "https://en.wikipedia.org/wiki/South_Sudanese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ssd.heritage.boma-national-park"
     :culture/name "Boma National Park"
     :culture/country "SSD"
     :culture/kind :heritage
     :culture/summary "Protected area in eastern South Sudan near the Ethiopian border, established in 1977, covering 22,800 sq km of grasslands and floodplains."
     :culture/url "https://en.wikipedia.org/wiki/Boma_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ssd.heritage.sudd"
     :culture/name "Sudd"
     :culture/country "SSD"
     :culture/kind :heritage
     :culture/summary "Vast swamp in South Sudan formed by the White Nile's Bahr al-Jabal section, one of the world's largest wetlands, designated a Ramsar Wetland of International Importance in 2006 (57,000 sq km)."
     :culture/url "https://en.wikipedia.org/wiki/Sudd"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-ssd culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SSD"))
                 " SSD entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))

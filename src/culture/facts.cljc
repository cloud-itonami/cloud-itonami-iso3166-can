(ns culture.facts
  "Country-level regional-culture catalog for Canada (CAN) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"CAN"
   [{:culture/id "can.dish.poutine"
     :culture/name "Poutine"
     :culture/country "CAN"
     :culture/kind :dish
     :culture/summary "Dish of french fries and cheese curds topped with hot gravy that emerged in the Centre-du-Québec region of Quebec in 1959; internationally often identified as Canadian, an identification some scholars regard as appropriation of Québécois culture."
     :culture/url "https://en.wikipedia.org/wiki/Poutine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.dish.butter-tart"
     :culture/name "Butter tart"
     :culture/country "CAN"
     :culture/kind :dish
     :culture/summary "Small pastry tart of butter, brown sugar and eggs, highly regarded in Canadian cuisine and commemorated on a 2019 Canada Post stamp in the Sweet Canada series."
     :culture/url "https://en.wikipedia.org/wiki/Butter_tart"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.dish.nanaimo-bar"
     :culture/name "Nanaimo bar"
     :culture/country "CAN"
     :culture/kind :dish
     :culture/summary "No-bake layered bar dessert named after the Canadian city of Nanaimo, British Columbia, promoted as a classic Canadian dessert at Expo 86."
     :culture/url "https://en.wikipedia.org/wiki/Nanaimo_bar"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.dish.tourtiere"
     :culture/name "Tourtière"
     :culture/country "CAN"
     :culture/kind :dish
     :culture/summary "French Canadian meat pie originating from the province of Quebec, a traditional part of the Christmas réveillon and New Year's Eve meal, served throughout Canada."
     :culture/url "https://en.wikipedia.org/wiki/Tourtière"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.product.maple-syrup"
     :culture/name "Maple syrup"
     :culture/country "CAN"
     :culture/kind :product
     :culture/summary "Syrup made from maple-tree sap; Canada produces more than 75 percent of the world's maple syrup, with Quebec alone accounting for 72 percent of global output."
     :culture/url "https://en.wikipedia.org/wiki/Maple_syrup"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.beverage.ice-wine"
     :culture/name "Ice wine"
     :culture/country "CAN"
     :culture/kind :beverage
     :culture/summary "Dessert wine made from grapes frozen on the vine; Canada is the world's largest producer, making more icewine than all other countries combined, over 90 percent of it in Ontario."
     :culture/url "https://en.wikipedia.org/wiki/Ice_wine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.beverage.caesar"
     :culture/name "Caesar"
     :culture/country "CAN"
     :culture/kind :beverage
     :culture/summary "Cocktail of vodka, Clamato juice, hot sauce and Worcestershire sauce, invented in 1969 by Walter Chell in Calgary, Alberta; it remains primarily a Canadian phenomenon."
     :culture/url "https://en.wikipedia.org/wiki/Caesar_(cocktail)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.craft.cowichan-knitting"
     :culture/name "Cowichan knitting"
     :culture/country "CAN"
     :culture/kind :craft
     :culture/summary "Form of knitting characteristic of the Cowichan people of southeastern Vancouver Island, British Columbia, producing distinctive heavy patterned sweaters."
     :culture/url "https://en.wikipedia.org/wiki/Cowichan_knitting"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.festival.calgary-stampede"
     :culture/name "Calgary Stampede"
     :culture/country "CAN"
     :culture/kind :festival
     :culture/summary "Annual rodeo, exhibition and festival held every July in Calgary, Alberta, one of Canada's largest festivals with more than one million visitors."
     :culture/url "https://en.wikipedia.org/wiki/Calgary_Stampede"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "can.heritage.old-quebec"
     :culture/name "Old Quebec"
     :culture/name-local "Vieux-Québec"
     :culture/country "CAN"
     :culture/kind :heritage
     :culture/summary "Historic neighbourhood of Quebec City declared a UNESCO World Heritage Site on 3 December 1985 as the Historic District of Old Québec."
     :culture/url "https://en.wikipedia.org/wiki/Old_Quebec"
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
      :note (str "cloud-itonami-iso3166-can culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "CAN"))
                 " CAN entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))

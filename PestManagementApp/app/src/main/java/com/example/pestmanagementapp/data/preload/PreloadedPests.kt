package com.example.pestmanagementapp.data.preload

import com.example.pestmanagementapp.R
import com.example.pestmanagementapp.data.models.PestInfo

/*
 * Preloaded data was sourced from the University of California Statewide
 * Integrated Pest Management Program website: https://ipm.ucanr.edu/#gsc.tab=0
 *
 * This source has been cited in the dissertation report.
 */

fun getPreloadedPests(): List<PestInfo> {
    return listOf(
        PestInfo(
            label = "Aphids",
            name = "Green peach aphid, Cabbage aphid, others",
            overview = "Aphids are small, soft-bodied insects with long slender mouthparts that they use to pierce stems," +
                    " leaves, and other tender plant parts and suck out fluids " +
                    "Almost every plant has one or more aphid species that occasionally feed on it",
            environmentFound = "Common in gardens and crop fields, especially in warm weather (65°-80°F). " +
                    "Prevalent along upwind garden edges and near infested plants or weeds like sowthistle and mustards",
            commonSymptom = "Yellowing or curling leaves, Stunted growth, Sticky honeydew promoting black sooty mold",
            size = "1–10 mm",
            color = "Green, yellow, brown, red, or black; some appear waxy or woolly due to white or gray secretions",
            shape = "Pear-shaped with long antennae and a pair of tubelike cornicles projecting backward from the hind end",
            earlySign = "Curling or yellowing of leaves, Presence of honeydew, Small groups of aphids on leaves or stems",
            advancedSign = "Black sooty mold, Deformed flowers/fruits, Stunted shoots, Gall formations",
            lowThreshold = "10–20 aphids per plant \n Spray with a strong jet of water or apply insecticidal soap to dislodge and control",
            mediumThreshold = "50–100 aphids per plant \n Introduce ladybugs or apply neem oil",
            highThreshold = "100+ aphids per plant \n Use systemic insecticides (e.g., imidacloprid for woody plants) or contact insecticides (e.g., pyrethroids) as a last resort",
            biologicalControl = "Ladybugs, Lacewings, Syrphid fly larvae, Parasitic wasps that lay eggs inside aphids, Forming golden-brown mummies, Fungal pathogens can also kill aphids in humid conditions",
            culturalControl = "Remove weeds and debris to eliminate aphid habitats, Prune infested leaves or shoots, Use slow-release nitrogen fertilizers to avoid favoring aphid reproduction," +
                    "Apply reflective mulches to repel aphids and reduce virus transmission in vegetables, Manage ants with sticky bands or baits to prevent them from protecting aphids",
            chemicalControl = "Insecticidal soap, Neem oil, Horticultural oils (1–2% solution) for smothering aphids, Supreme- or superior-type oils for dormant applications on fruit trees," +
                    "Systemic insecticides like imidacloprid for severe infestations in woody plants and avoid use on blooming plants to protect pollinators",
            imageResId = R.drawable.aphid
        ),
        PestInfo(
            label = "Beetle",
            name = "Elm Leaf Beetle, Flea Beetle, others",
            overview = "Beetles, including the elm leaf beetle and Colorado potato beetle, " +
                    "are leaf-chewing pests that feed on foliage, causing significant damage by defoliating crops and trees like elms, potatoes, tomatoes, and eggplants ",
            environmentFound = "Common in gardens, crop fields (e.g., potato, tomato, eggplant), and landscapes with elms, especially European species. " +
                    "Prevalent in spring and summer, with elm leaf beetles having 1–3 generations per year depending on climate",
            commonSymptom = "Holes in leaves, Skeletonized foliage, Stunted growth, Browned or whitish leaves, Severe infestations can lead to crop loss or tree decline",
            size = "5–20 mm (adults); elm leaf beetle larvae up to 8.5 mm",
            color = "Varies: elm leaf beetle is olive-green with black stripes; Colorado potato beetle is yellow with black stripes; others may be brown, black, or metallic",
            shape = "Oval with a hard exoskeleton; elm leaf beetle larvae are caterpillar-like with dark tubercles",
            earlySign = "Small holes in leaves, Clusters of yellowish to gray eggs, Larvae on leaf undersides",
            advancedSign = "Skeletonized or browned leaves, Deformed flowers, Severe plant weakness, Tree decline",
            lowThreshold = "1–5 beetles or larvae per plant \n Hand-pick, use insecticidal soap, apply bark banding for elm leaf beetles",
            mediumThreshold = "6–20 beetles or larvae per plant \n Introduce natural predators (e.g., ladybugs, parasitic wasps) or apply neem oil or spinosad",
            highThreshold = "20+ beetles or larvae per plant \n Use systemic insecticides (e.g., imidacloprid, dinotefuran) or bark banding with residual insecticides (e.g., carbaryl) for elm leaf beetles",
            biologicalControl = "Ladybugs, Lacewing larvae, Predaceous ground beetles, Parasitic wasps such as Oomyzus gallerucae for elm leaf beetle eggs)," +
                    " Tachinid flies such as Erynniopsis antennata for elm leaf beetle larvae",
            culturalControl = "Rotate crops, Remove crop debris and dead branches, Choose resistant cultivars such as Chinese elms or Accolade for elms," +
                    " Provide appropriate irrigation to maintain tree vigor, Avoid excessive pruning and protect tree trunks/roots from injury.",
            chemicalControl = "Insecticidal soap, Neem oil, Nonresidual contact insecticides such as azadirachtin or pyrethrins for small infestations," +
                    "Spinosad for short-residual foliar sprays" +
                    " Systemic insecticides such as imidacloprid or dinotefuran via soil drench or trunk application for severe cases",
            imageResId = R.drawable.beetle
        ),
            PestInfo(
                label = "Caterpillar",
                name = "Tomato Fruitworm, Cabbage Worm, others",
                overview = "Caterpillars, the larvae of moths and butterflies, cause extensive damage by chewing leaves and fruit." +
                        " Tomato fruitworm, also known as cotton bollworm or corn earworm, is a medium-sized moth larva that bores into fruit," +
                        " while others like cabbage worm and tomato hornworm primarily defoliate crops",
                environmentFound = "Common in leafy vegetables (e.g., cabbage, broccoli, lettuce), fruiting crops (e.g., tomatoes, corn, cotton), and fields, especially from July to September",
                commonSymptom = "Ragged leaf edges, Large holes in leaves, Watery cavities in fruit with feces, Premature fruit ripening and droppings, " +
                        "Severe infestations may stunt plants or cause crop loss",
                size = "1–5 cm (larvae); tomato fruitworm adults have 25–35 mm wingspan",
                color = "Varies: tomato fruitworm larvae are creamy white to yellowish green or black with black spots and bristles; cabbage worm is green; tomato hornworm is green with stripes",
                shape = "Long, cylindrical body with prolegs; tomato fruitworm larvae have black tubercles and stubby spines",
                earlySign = "Small holes or irregular leaf damage, Creamy white eggs with coarse striations on leaves",
                advancedSign = "Extensive leaf damage, Large fecal pellets, Watery fruit cavities with cast skins, Deformed or unmarketable fruit",
                lowThreshold = "1–5 caterpillars or eggs per plant \n Hand-pick, spray with insecticidal soap, or monitor for parasitized eggs",
                mediumThreshold = "6–15 caterpillars or 3–5 healthy eggs per 30 leaves \n Apply Bacillus thuringiensis (Bt), neem oil, or release Trichogramma parasites",
                highThreshold = "15+ caterpillars or 5+ healthy eggs per 30 leaves \n Use spinosad, Entrust (organic), or pyrethrins, targeting newly hatched larvae",
                biologicalControl = "Trichogramma spp. egg parasites, Hyposoter exiguae larval parasites, Predators like bigeyed bugs and minute pirate bugs, Bacillus thuringiensis (Bt) for organic control",
                culturalControl = "Remove crop debris, Practice crop rotation, Monitor with pheromone traps to detect adult flights, Sample leaves for eggs",
                chemicalControl = "Bacillus thuringiensis (Bt), Spinosad (Entrust for organic), Neem oil, Pyrethrins for newly hatched larvae, Avoid broad-spectrum insecticides to preserve natural enemies like Trichogramma parasites",
                imageResId = R.drawable.caterpillar
            ),
        PestInfo(
            label = "Leafhopper",
            name = "Cicadella viridis, others",
            overview = "Leafhoppers are wedge-shaped insects that feed on plant sap, causing stippling and potentially transmitting plant pathogens like Xylella fastidiosa, aster yellows, and curly top virus." +
                    " Some, like the glassy-winged sharpshooter, are larger and known as sharpshooters.",
            environmentFound = "Common in crops (e.g., beans, tomatoes, peppers, grapes) and landscapes, especially on weeds, evergreen hosts like citrus, or vegetables." +
                    " Prevalent year-round in mild winters, with 2+ generations annually.",
            commonSymptom = "Yellowing (stippling), Curling leaves, Stunted growth, Blackish sooty mold on honeydew, Whitish cast skins on leaf undersides," +
                    " High numbers or disease transmission can severely damage plants",
            size = "3–13 mm; most under 6 mm, glassy-winged sharpshooter up to 13 mm",
            color = "Brown, gray, green, yellow, or mottled; some brightly colored or blend with host plants",
            shape = "Wedge-shaped, long relative to width, with long rows of spines on hind legs",
            earlySign = "Yellow stippling (bleached specks) on leaves, Slight curling of leaf edges, Nymphs or cast skins on leaf undersides",
            advancedSign = "Severe yellowing or browning, Curled or dying leaves, Stunted growth, Disease symptoms ",
            lowThreshold = "1–2 leafhoppers or nymphs per plant \n Spray with horticultural oil, insecticidal soap, or water to dislodge",
            mediumThreshold = "3–5 leafhoppers or nymphs per plant \n Introduce predators (e.g., lacewings, minute pirate bugs) or apply neem oil",
            highThreshold = "5+ leafhoppers or nymphs per plant \n Use systemic insecticides (e.g., imidacloprid) or contact insecticides (e.g., pyrethroids)," +
                    " avoiding broad-spectrum to preserve natural enemies",
            biologicalControl = "Assassin bugs, Brown and green lacewings, Damsel bugs, Lady beetles",
            culturalControl = "Remove weeds and alternative host plants to reduce leafhopper migration, Inspect plants for nymphs starting late winter," +
                    " Maintain plant health to tolerate minor damage",
            chemicalControl = "Horticultural oil, Insecticidal soap, Neem oil for nymphs and minor infestations, Systemic insecticides such as imidacloprid) or pyrethroids for severe cases," +
                    " Applied carefully to avoid harming parasitoids and pollinators",
            imageResId = R.drawable.leafhopper
        ),

        PestInfo(
            label = "Mealybugs",
            name = "Grape Mealybug, Longtailed Mealybug, others",
            overview = "Mealybugs are soft, oval, wax-covered insects in the Pseudococcidae family that suck plant sap, often forming colonies in protected areas." +
                    " They produce honeydew, leading to black sooty mold, and some species transmit plant viruses",
            environmentFound = "Common in greenhouses, houseplants, fruit crops (e.g., citrus, grapes), and woody ornamentals." +
                    " Favored by warm weather, with 2–6 generations per year in mild climates or indoors",
            commonSymptom = "Wilting, Yellowing leaves, Stunted growth, Black sooty mold from honeydew, Waxy cottony masses, High populations can cause leaf drop or " +
                    "plant decline",
            size = "1–6 mm; adult males smaller with wings",
            color = "White to pinkish, covered with white wax; crawlers yellow to orangish",
            shape = "Oval, distinctly segmented, soft-bodied with wax filaments",
            earlySign = "White, Cottony masses, Crawlers on plant joints, Stems or leaf undersides",
            advancedSign = "Wilting, Severe yellowing, Black sooty mold, Leaf drop, Root damage (ground mealybugs)",
            lowThreshold = "1–2 mealybugs or egg sacs per plant \n Hand-pick, prune infested parts, or spray with water",
            mediumThreshold = "3–5 mealybugs or egg sacs per plant \n Apply insecticidal soap, neem oil, or 10–25% isopropyl alcohol",
            highThreshold = "5+ mealybugs or egg sacs per plant \n Use systemic insecticides (e.g., dinotefuran, imidacloprid) or release mealybug destroyers",
            biologicalControl = "Parasitic wasps such as Leptomastix dactylopii or Acerophagus spp., Mealybug destroyer lady beetle (Cryptolaemus montrouzieri), " +
                    "Green lacewings, and minute pirate bugs",
            culturalControl = "Inspect new plants, Prune infested parts, Remove old plants, Avoid excess nitrogen fertilizer, Control ants to protect natural enemies," +
                    " Discard heavily infested houseplants",
            chemicalControl = "Insecticidal soap, Horticultural oil, Neem oil for nymphs, Spot-treat with 10–25% isopropyl alcohol, Systemic insecticides such as dinotefuran or imidacloprid) for severe cases",
            imageResId = R.drawable.mealybug
        ),
        PestInfo(
            label = "Slugs",
            name = "Brown Garden Snail, Banded Slug, others",
            overview = "Snails and slugs are soft-bodied mollusks that feed at night, leaving irregular holes and silvery slime trails" +
                    " Snails have external spiral shells, while slugs lack them. Both are hermaphrodites, causing significant damage to seedlings," +
                    " fruits, and foliage",
            environmentFound = "Common in damp or shaded gardens, vegetable fields, and coastal areas with mild winters. Active year-round in mild climates, " +
                    "hiding in debris or under boards during hot or dry periods",
            commonSymptom = "Shredded leaves, Irregular holes in foliage/flowers, Chewed seedlings/fruits, Silvery slime trails," +
                    " Severe infestations can destroy seedlings or damage ripening fruits.",
            size = "1–10 cm; brown garden snail up to 3 cm, slugs vary by species",
            color = "Gray, brown, black, or yellowish; snails with spiral shells",
            shape = "Slugs: elongated, soft, slimy body with tentacled head; snails: similar but with external spiral shell",
            earlySign = "Silvery slime trails on soil/leaves, Minor chewing on foliage",
            advancedSign = "Large holes in leaves/fruits, Seedling destruction, Clipped succulent plant parts",
            lowThreshold = "1–2 snails or slugs/m² \n Hand-pick, set beer or yeast traps",
            mediumThreshold = "3–5 snails or slugs/m² \n Use copper barriers or apply predatory decollate snails where permitted",
            highThreshold = "5+ snails or slugs/m² \n Apply iron phosphate or ferric sodium EDTA baits, avoiding metaldehyde near pets",
            biologicalControl = "Ground beetles, Toads, Snakes, Birds, and Decollate snails (Rumina decollata) in permitted areas." +
                    " Domestic fowl such as ducks can reduce populations",
            culturalControl = "Remove mulch, Weeds, Debris, Hiding spots like boards or ivy, Use drip irrigation, Water early, Solarize soil to kill eggs" +
                    " Plant resistant species such as lavender, rosemary",
            chemicalControl = "Iron phosphate or ferric sodium EDTA baits for safer control; metaldehyde baits as a last resort, " +
                    "Applied sparingly away from pets and edible plants, Sprinkle baits near hiding spots in late afternoon",
            imageResId = R.drawable.slug
        ),
        PestInfo(
            label = "Sowbug",
            name = "Pillbug, Sowbug, Woodlouse",
            overview = "Pillbugs and sowbugs are soil-dwelling crustaceans in the order Isopoda that primarily feed on decaying plant material but may " +
                    "damage seedlings, roots, and fruits touching damp soil. Pillbugs can roll into a ball, while sowbugs cannot",
            environmentFound = "Common in damp, shaded areas under organic litter, mulch, or debris, especially near low-growing plants or moist soil",
            commonSymptom = "Chewed seedlings, Gnawed roots or lower leaves, Damaged fruits/vegetables touching moist soil, Indoor presence can be a nuisance",
            size = "0.8–2 cm",
            color = "Brown to dark gray, sometimes blue/purplish when newly molted; young are pale yellow to whitish",
            shape = "Oval, segmented, with seven pairs of legs; pillbugs have rounded rear, sowbugs have protruding rear appendages",
            earlySign = "Presence under pots, Mulch, Debris; minor root or seedling damage",
            advancedSign = "Damaged seedlings, Gnawed stems/roots, Chewed fruits/vegetables in contact with soil",
            lowThreshold = "1–2 per area \n Remove debris, reduce irrigation frequency",
            mediumThreshold = "3–10 per area \n Use plastic mulch or raised beds, apply diatomaceous earth",
            highThreshold = "10+ per area \n Use traps or pyrethrin-based insecticides sparingly",
            biologicalControl = "Ground beetles, birds, and centipedes. Encourage natural predators by minimizing broad-spectrum insecticides",
            culturalControl = "Reduce moisture, Remove decaying matter, Keep mulch away from plants, Use drip irrigation, Raised beds, Black plastic mulch," +
                    "Seal building gaps to prevent indoor entry",
            chemicalControl = "Pyrethrin-based insecticides for severe cases; avoid baits unless necessary, Focus on habitat modification for long-term control",
            imageResId = R.drawable.sowbug
        ),
        PestInfo(
            label = "Spider Mite",
            name = "Pacific Spider Mite, Twospotted Spider Mite, others",
            overview = "Spider mites are tiny arachnids in the Tetranychus genus that feed on plant sap, causing stippling, webbing, and leaf drop. " +
                    "They produce silk webbing on infested leaves and thrive in hot, dusty conditions",
            environmentFound = "Common in hot, dry environments, greenhouses, and on fruit trees, vines, berries, vegetables, and ornamentals. " +
                    "Abundant June–September, year-round on evergreens in mild climates",
            commonSymptom = "Stippling (light dots), Yellowing or bronzing leaves, Fine webbing, Leaf drop, High populations can reduce crop yield or kill " +
                    "annual plants",
            size = "Less than 1 mm",
            color = "Red, green, yellow, or orange; females often with dark blotches",
            shape = "Tiny, oval body with eight legs; larvae have six legs",
            earlySign = "Fine stippling and webbing on leaf undersides, Tiny moving dots",
            advancedSign = "Bronze or reddish leaves, Extensive webbing, Leaf drop, Plant death in extreme cases",
            lowThreshold = "1–5 mites/leaf \n Spray water forcefully or apply insecticidal soap",
            mediumThreshold = "6–20 mites/leaf \n Release predatory mites (e.g., Galendromus occidentalis), apply neem oil",
            highThreshold = "20+ mites/leaf \n Use selective miticides (e.g., abamectin, horticultural oil), avoid broad-spectrum insecticides",
            biologicalControl = "Predatory mites such as Galendromus occidentalis or Phytoseiulus spp., Sixspotted thrips, Spider mite destroyer lady beetle",
            culturalControl = "Provide adequate irrigation, Wash plants with water to reduce dust, Avoid water stress, Minimize broad-spectrum insecticides to " +
                    "protect natural enemies",
            chemicalControl = "Horticultural oils such as neem or canola, Insecticidal soap, Plant-based acaricides such as rosemary oil," +
                    "Sulfur sprays for some crops",
            imageResId = R.drawable.spidermite
        ),
        PestInfo(
            label = "Thrips",
            name = "Western Flower Thrips, Onion Thrips, others",
            overview = "Thrips are tiny, slender insects with fringed wings that puncture plant tissues, causing silvering, stippling, and distorted growth." +
                    " Some species vector plant viruses, while others are beneficial predators of insects and mites",
            environmentFound = "Common in flowers, vegetables (e.g., onions, tomatoes), fruit crops, and ornamentals, especially in warm weather." +
                    " Often introduced via infested plants or wind dispersal",
            commonSymptom = "Silver streaks, Stippling, Black frass dots, Distorted flowers/buds, Scarred fruit, Virus transmission can cause severe plant damage",
            size = "0.5–2 mm",
            color = "Translucent white, yellow, brown, or black; some brightly colored (e.g., reddish-orange larvae)",
            shape = "Slender, elongate body with fringed wings; immatures wingless",
            earlySign = "Silvery streaks, Stippling, Black frass specks on leaves or flowers",
            advancedSign = "Leaf curling, Browning, Distorted growth, Poor fruiting, Virus symptoms",
            lowThreshold = "1–5 thrips per flower/leaf \n Use yellow sticky traps, apply insecticidal soap",
            mediumThreshold = "6–10 thrips per flower/leaf \n Release predators (e.g., minute pirate bugs, predatory thrips), use neem oil",
            highThreshold = "10+ thrips per flower/leaf \n Apply spinosad or selective insecticides (e.g., abamectin), avoid broad-spectrum options",
            biologicalControl = "Minute pirate bugs, Predatory thrips such as Franklinothrips spp., Green lacewings," +
                    " Euseius mites, Parasitic wasps such as Thripobius semiluteus",
            culturalControl = "Remove weeds and old flowers, use reflective mulch, Prune infested terminals, Select resistant cultivars," +
                    "Use row covers for young crops",
            chemicalControl = "Insecticidal soap, Neem oil, Spinosad for contact control, Selective insecticides such as abamectin or azadirachtin for severe cases",
            imageResId = R.drawable.thrips
        ),
        PestInfo(
            label = "Weevil",
            name = "Black Vine Weevil, Rice Weevil, others",
            overview = "Weevils are hard-bodied, snout beetles that bore into roots, stems, or grains, causing wilting, notching, and crop damage." +
                    " The black vine weevil is a common pest of garden and landscape plants",
            environmentFound = "Common in stored grains, root crops, and landscapes with host plants like azalea, rhododendron, and strawberries." +
                    " Adults hide in soil or litter during the day",
            commonSymptom = "Notched or ragged leaves, Wilting plants, Root damage, Holes in grains, Larval feeding can weaken or kill plants",
            size = "2–12 mm; black vine weevil about 12 mm",
            color = "Black with white scales, brown, or reddish-brown",
            shape = "Hard-bodied beetle with elongated snout and elbowed antennae",
            earlySign = "Leaf notching, Boring holes in seeds/roots, Adult weevils on foliage at night",
            advancedSign = "Wilting, significant root damage, Grain dust, Plant death",
            lowThreshold = "1–2 weevils per plant/kg \n Hand-pick adults, remove infested material",
            mediumThreshold = "3–5 weevils per plant/kg \n Use pitfall traps, burlap wraps, or apply nematodes",
            highThreshold = "5+ weevils per plant/kg \n Apply spinosad to foliage or systemic insecticides (e.g., imidacloprid) to soil",
            biologicalControl = "Parasitic nematodes such as Heterorhabditis spp. or Steinernema spp.," +
                    "Predatory beetles, Birds",
            culturalControl = "Clean storage areas, Rotate crops, Select resistant cultivars, Check plant roots before planting, Apply sticky barriers to trunks",
            chemicalControl = "Spinosad for foliar control, Diatomaceous earth for stored grains, Systemic insecticides such as imidacloprid for soil application," +
                    " Avoid broad-spectrum insecticides to preserve natural enemies",
            imageResId = R.drawable.weevil
        )
    )
}
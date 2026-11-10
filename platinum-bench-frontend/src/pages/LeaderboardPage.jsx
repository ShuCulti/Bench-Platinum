import { useState } from "react"
import { useNavigate } from "react-router"
import { Navbar } from "../components/NavBar.jsx"
import { Footer } from "../components/Footer.jsx"
import "../components/Components.css"
import "./LeaderboardPage.css"

/* Sample runs until the runs endpoint exists, then this becomes api.get("/runs") in a useEffect */
const runs = [
    {id: 1042, hardware: "NVIDIA RTX 5070", type: "GPU", vendor: "NVIDIA", quant: "Q4_K_M", gen: 98.4, spread: 1.2, prompt: 2310, tpw: 0.54, by: "ShuCulti", complete: true},
    {id: 1038, hardware: "Apple M3 Max (40c GPU)", type: "Apple", vendor: "Apple", quant: "Q4_K_M", gen: 71.2, spread: 0.6, prompt: 640, tpw: 1.98, by: "mlx_fan", complete: true},
    {id: 1031, hardware: "AMD RX 7800 XT", type: "GPU", vendor: "AMD", quant: "Q4_K_M", gen: 69.8, spread: 2.4, prompt: 1480, tpw: 0.37, by: "redteam", complete: true},
    {id: 1027, hardware: "Intel Arc B580", type: "GPU", vendor: "Intel", quant: "Q4_K_M", gen: 62.1, spread: 0.9, prompt: 1120, tpw: 0.46, by: "ShuCulti", complete: true},
    {id: 1019, hardware: "NVIDIA RTX 3060 12GB", type: "GPU", vendor: "NVIDIA", quant: "Q4_K_M", gen: 55.6, spread: 0.4, prompt: 1350, tpw: 0.37, by: "budgetbench", complete: true},
    {id: 1012, hardware: "Apple M2 Pro", type: "Apple", vendor: "Apple", quant: "Q4_K_M", gen: 38.9, spread: 0.3, prompt: 310, tpw: 1.62, by: "mlx_fan", complete: true},
    {id: 1008, hardware: "NVIDIA RTX 5070", type: "GPU", vendor: "NVIDIA", quant: "Q8_0", gen: 63.2, spread: 0.8, prompt: 1940, tpw: 0.35, by: "ShuCulti", complete: true},
    {id: 1004, hardware: "AMD Ryzen 9 5950X (CPU)", type: "CPU", vendor: "AMD", quant: "Q4_K_M", gen: 11.3, spread: 0.2, prompt: 96, tpw: 0.09, by: "ShuCulti", complete: true},
    {id: 998, hardware: "AMD Ryzen 7 3700X (CPU)", type: "CPU", vendor: "AMD", quant: "Q4_K_M", gen: 7.8, spread: 0.1, prompt: 61, tpw: 0.07, by: "ShuCulti", complete: false},
]

const quants = ["Q4_K_M", "Q5_K_M", "Q8_0", "F16"]
const deviceTypes = [
    {value: "GPU", label: "GPU"},
    {value: "CPU", label: "CPU"},
    {value: "Apple", label: "Apple unified memory"},
]
const vendors = ["All vendors", "NVIDIA", "AMD", "Intel", "Apple"]

export default function LeaderboardPage(){
    const navigate = useNavigate()
    const [selected, setSelected] = useState([1042, 1038])
    const [sortBy, setSortBy] = useState("gen")
    const [activeQuants, setActiveQuants] = useState(["Q4_K_M"])
    const [activeTypes, setActiveTypes] = useState(["GPU", "CPU", "Apple"])
    const [vendor, setVendor] = useState("All vendors")
    const [showIncomplete, setShowIncomplete] = useState(false)

    function toggleRun(id){
        setSelected((prev) =>
            prev.includes(id) ? prev.filter((i) => i !== id) : [...prev, id]
        )
    }

    function toggleQuant(quant){
        setActiveQuants((prev) =>
            prev.includes(quant) ? prev.filter((q) => q !== quant) : [...prev, quant]
        )
    }

    function toggleType(type){
        setActiveTypes((prev) =>
            prev.includes(type) ? prev.filter((t) => t !== type) : [...prev, type]
        )
    }

    function resetFilters(){
        setActiveQuants(["Q4_K_M"])
        setActiveTypes(["GPU", "CPU", "Apple"])
        setVendor("All vendors")
        setShowIncomplete(false)
    }

    const filteredRuns = runs.filter((run) => {
        const matchesQuant = activeQuants.includes(run.quant)
        const matchesType = activeTypes.includes(run.type)
        const matchesVendor = vendor === "All vendors" || run.vendor === vendor
        const matchesComplete = showIncomplete || run.complete
        return matchesQuant && matchesType && matchesVendor && matchesComplete
    })

    const sortedRuns = [...filteredRuns].sort((a, b) => b[sortBy] - a[sortBy])

    const canCompare = selected.length >= 2 && selected.length <= 4

    return(
        <>
            <div className="page">
                <Navbar/>
                <main className="page-main">
                    <div className="Leaderboard-header">
                        <div>
                            <h1 className="page-title">Leaderboard</h1>
                            <p className="page-subtitle">
                                Every finished, complete run. Speeds are the median of all repetitions, with the spread next to it.
                                Incomplete runs are hidden unless you ask for them.
                            </p>
                        </div>
                        <label className="select-label">Sort by
                            <select className="select" value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
                                <option value="gen">Generation t/s (high to low)</option>
                                <option value="tpw">Tokens per watt (high to low)</option>
                                <option value="prompt">Prompt t/s (high to low)</option>
                                <option value="id">Newest first</option>
                            </select>
                        </label>
                    </div>

                    <div className="Leaderboard-body">
                        <LeaderboardFilters
                            activeQuants={activeQuants}
                            toggleQuant={toggleQuant}
                            activeTypes={activeTypes}
                            toggleType={toggleType}
                            vendor={vendor}
                            setVendor={setVendor}
                            showIncomplete={showIncomplete}
                            setShowIncomplete={setShowIncomplete}
                            resetFilters={resetFilters}
                        />

                        <section className="Leaderboard-results">
                            <div className="Leaderboard-compare-bar">
                                <span className="Leaderboard-muted">
                                    <span className="Leaderboard-count">{selected.length}</span> selected · pick 2 to 4 runs to compare
                                </span>
                                <button className="btn-primary" disabled={!canCompare} onClick={() => navigate("/compare")}>
                                    Compare selected
                                </button>
                            </div>

                            <LeaderboardTable
                                runs={sortedRuns}
                                selected={selected}
                                toggleRun={toggleRun}
                                navigate={navigate}
                            />

                            <div className="Leaderboard-pages">
                                <span>Showing 1–{sortedRuns.length} of 312 runs</span>
                                <div className="Leaderboard-page-buttons">
                                    <button className="Leaderboard-page-btn" disabled>Prev</button>
                                    <button className="Leaderboard-page-btn Leaderboard-page-btn-active">1</button>
                                    <button className="Leaderboard-page-btn">2</button>
                                    <button className="Leaderboard-page-btn">3</button>
                                    <button className="Leaderboard-page-btn">Next</button>
                                </div>
                            </div>
                        </section>
                    </div>
                </main>
                <Footer/>
            </div>
        </>
    )
}

function LeaderboardFilters({ activeQuants, toggleQuant, activeTypes, toggleType, vendor, setVendor, showIncomplete, setShowIncomplete, resetFilters }){
    return(
        <>
            <aside className="card Leaderboard-filters">
                <div className="Leaderboard-filters-header">
                    <h2 className="Leaderboard-filters-title">Filters</h2>
                    <span className="Leaderboard-reset" onClick={resetFilters}>Reset</span>
                </div>

                <label className="select-label">Model
                    <select className="select">
                        <option>Qwen2.5-7B</option>
                        <option>Llama-3.1-8B</option>
                        <option>Gemma-3-12B</option>
                        <option>All models</option>
                    </select>
                </label>

                <div className="Leaderboard-group">
                    <p className="Leaderboard-group-title">Quant</p>
                    {quants.map((quant) => (
                        <label key={quant} className="Leaderboard-check Leaderboard-check-mono">
                            <input type="checkbox" checked={activeQuants.includes(quant)} onChange={() => toggleQuant(quant)}/>
                            {quant}
                        </label>
                    ))}
                </div>

                <div className="Leaderboard-group">
                    <p className="Leaderboard-group-title">Device type</p>
                    {deviceTypes.map((type) => (
                        <label key={type.value} className="Leaderboard-check">
                            <input type="checkbox" checked={activeTypes.includes(type.value)} onChange={() => toggleType(type.value)}/>
                            {type.label}
                        </label>
                    ))}
                </div>

                <label className="select-label">Vendor
                    <select className="select" value={vendor} onChange={(e) => setVendor(e.target.value)}>
                        {vendors.map((v) => (
                            <option key={v} value={v}>{v}</option>
                        ))}
                    </select>
                </label>

                <label className="select-label">Engine build
                    <select className="select">
                        <option>Latest builds only</option>
                        <option>All builds</option>
                    </select>
                </label>

                <label className="select-label">Context size
                    <select className="select">
                        <option>4096</option>
                        <option>8192</option>
                        <option>32768</option>
                        <option>Any</option>
                    </select>
                </label>

                <label className="Leaderboard-check Leaderboard-muted">
                    <input type="checkbox" checked={showIncomplete} onChange={() => setShowIncomplete(!showIncomplete)}/>
                    Show incomplete runs
                </label>
            </aside>
        </>
    )
}

function LeaderboardTable({ runs, selected, toggleRun, navigate }){
    return(
        <>
            <div className="table-wrapper">
                <table className="table">
                    <thead>
                        <tr>
                            <th></th>
                            <th>#</th>
                            <th>Hardware</th>
                            <th>Model file</th>
                            <th className="table-right">Gen t/s</th>
                            <th className="table-right">Prompt t/s</th>
                            <th className="table-right">Tokens/W</th>
                            <th>Engine</th>
                            <th>By</th>
                        </tr>
                    </thead>
                    <tbody>
                        {runs.length === 0 && (
                            <tr>
                                <td colSpan={9} className="Leaderboard-empty">No runs match these filters.</td>
                            </tr>
                        )}
                        {runs.map((run, index) => (
                            <tr key={run.id} className={selected.includes(run.id) ? "Leaderboard-row-selected" : ""}>
                                <td>
                                    <input
                                        type="checkbox"
                                        className="Leaderboard-row-check"
                                        checked={selected.includes(run.id)}
                                        onChange={() => toggleRun(run.id)}
                                        aria-label={`Select run ${run.id} for comparing`}
                                    />
                                </td>
                                <td className="Leaderboard-muted">{index + 1}</td>
                                <td className="Leaderboard-link" onClick={() => navigate("/hardware")}>{run.hardware}</td>
                                <td className="Leaderboard-link Leaderboard-muted Leaderboard-nowrap" onClick={() => navigate(`/runs/${run.id}`)}>
                                    Qwen2.5-7B · {run.quant}
                                </td>
                                <td className="table-right Leaderboard-nowrap">
                                    <span className="Leaderboard-gen">{run.gen}</span>{" "}
                                    <span className="Leaderboard-spread">±{run.spread}</span>
                                </td>
                                <td className="table-right">{run.prompt}</td>
                                <td className="table-right Leaderboard-tpw">{run.tpw}</td>
                                <td className="Leaderboard-muted">[BUILD]</td>
                                <td className="Leaderboard-muted">
                                    {run.by}
                                    {!run.complete && <span className="Leaderboard-incomplete">incomplete</span>}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    )
}

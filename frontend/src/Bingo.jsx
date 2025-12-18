import { useEffect, useState } from "react";
import api from "./api";
import { getPhraseStyle } from "./phraseTypeHandler";

export default function Bingo() {
    const email = localStorage.getItem("email");
    const [showInfo, setShowInfo] = useState(true);
    const [card, setCard] = useState(null);
    const [pending, setPending] = useState([]);
    

    if (!email) {
        window.location.href = "/login";
        return null; // escape before other calls made
    }


    async function loadCard() {
        try {
            const res = await api.get("/card", { params: { email } });
            setCard(res.data);
            setPending(new Array(res.data.crossed.length).fill(null));
        } catch (err) {
            console.warn("Network error or server unavailable");
            console.error(err);
            if (err.response?.status === 401 || err.response?.status === 403) {
                localStorage.removeItem("email");
                window.location.href = "/login";
            }
        }
    }

    const getEffectiveCrossed = () =>
    card.crossed.map((v, i) => pending[i] ?? v);

    const effectiveCrossed = getEffectiveCrossed();


    const toggle = async (index) => {
        if (!card) return;
    
        const nextPending = [...pending];
        const effective = pending[index] ?? card.crossed[index];
        nextPending[index] = !effective;
    
        setPending(nextPending);
        const desiredCrossed = card.crossed.map(
            (v, i) => nextPending[i] ?? v
        );
    
        try {
            const res = await api.post("/sync", {
                email,
                crossed: desiredCrossed
            });
    
            setCard(prev => ({
                ...prev,
                crossed: res.data.crossed,
                bingo: res.data.bingo
            }));
    
            setPending(prev =>
                prev.map((p, i) =>
                    p === null || p === res.data.crossed[i] ? null : p
                )
            );
        } catch (err) {
            console.error("Sync failed:", err);
        }
    };
    

    const handleLogout = () => {
        localStorage.removeItem("email");
        window.location.href = "/login";
    };

    useEffect(() => { loadCard(); }, []);

    if (!card?.phrases) return <div>Loading...</div>;

    const isDark = true; // simple toggle, could also read from user preference

    return (
        <div style={{ padding: 10, color: isDark ? "#eee" : "#000", backgroundColor: isDark ? "#121212" : "#fff", minHeight: "100vh" }}>

            {/* Header */}
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 10 }}>
                <h2 style={{ margin: 0 }}>Bingo</h2>
                <div style={{ display: "flex", gap: 8, alignItems: "center" }}>
                    <button 
                        onClick={() => setShowInfo(true)}
                        style={{
                            fontSize: 18,
                            width: 30,
                            height: 30,
                            borderRadius: "50%",
                            border: `1px solid ${isDark ? "#555" : "#ccc"}`,
                            background: isDark ? "#222" : "#fff",
                            color: isDark ? "#eee" : "#000",
                            cursor: "pointer",
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center"
                        }}
                    >
                        i
                    </button>
                    <button 
                        onClick={handleLogout} 
                        style={{
                            padding: 6,
                            border: `1px solid ${isDark ? "#555" : "#ccc"}`,
                            background: isDark ? "#222" : "#fff",
                            color: isDark ? "#eee" : "#000",
                            cursor: "pointer",
                            borderRadius: 4
                        }}
                    >
                        Logout
                    </button>
                </div>
            </div>

            {/* Bingo grid */}
            <div
                style={{
                    display: "grid",
                    gridTemplateColumns: "repeat(5, 1fr)",
                    gap: 4,
                    width: "100%",
                    maxWidth: "100vw",
                    boxSizing: "border-box",
                }}
            >
                {card.phrases.map((phrase, i) => {
                    const isPending = pending[i] !== null;
                    const isCrossed = effectiveCrossed[i];
                    
                    const bg = isCrossed
                        ? isPending ? "#AED581" : "#8BC34A"
                        : style.backgroundColor;
                    
                    const style = getPhraseStyle(phrase, card.crossed[i]);
                    const color = card.crossed[i] ? "#fff" : style.color;

                    return (
                        <button
                            key={i}
                            onClick={() => toggle(i)}
                            style={{
                                ...style,
                                width: "100%",
                                aspectRatio: "1 / 1",
                                fontSize: "clamp(8px, 1.2vw, 12px)",
                                padding: 4,
                                borderRadius: 4,
                                overflowWrap: "break-word",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                                position: "relative",
                                textAlign: "center",
                                boxSizing: "border-box",
                                backgroundColor: bg,
                                color: color,
                                opacity: isPending ? 0.7 : 1,
                                border: "1px solid #888"
                            }}
                        >
                            {style.emoji && (
                                <span
                                    style={{
                                        position: "absolute",
                                        fontSize: "3rem",
                                        opacity: 0.1,
                                        pointerEvents: "none",
                                        userSelect: "none"
                                    }}
                                >
                                    {style.emoji}
                                </span>
                            )}
                            <span style={{ position: "relative", zIndex: 1 }}>
                                {phrase.text}
                            </span>
                        </button>
                    );
                })}
            </div>

            {/* Bingo popup */}
            {card.bingo && (
                <div style={{
                    position: "fixed",
                    top: "50%",
                    left: "50%",
                    transform: "translate(-50%, -50%)",
                    backgroundColor: "#4CAF50",
                    color: "white",
                    padding: "15px 30px",
                    borderRadius: 8,
                    boxShadow: "0 4px 6px rgba(0,0,0,0.3)",
                    fontSize: 18,
                    zIndex: 1000,
                    textAlign: "center",
                    animation: "fadein 0.5s"
                }}>
                    🎉 BINGO! 🎉
                </div>
            )}

            {/* Info modal */}
            {showInfo && (
                <div style={{
                    position: "fixed",
                    top: 0,
                    left: 0,
                    width: "100vw",
                    height: "100vh",
                    backgroundColor: "rgba(0,0,0,0.8)",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    zIndex: 1000
                }}>
                    <div style={{
                        backgroundColor: isDark ? "#1E1E1E" : "#fff",
                        color: isDark ? "#eee" : "#000",
                        maxWidth: "90%",
                        maxHeight: "90%",
                        padding: 20,
                        borderRadius: 8,
                        overflowY: "auto",
                        boxShadow: "0 4px 10px rgba(0,0,0,0.5)",
                        position: "relative"
                    }}>
                        <button 
                            onClick={() => setShowInfo(false)}
                            style={{
                                position: "absolute",
                                top: 10,
                                right: 10,
                                fontSize: 24,
                                border: "none",
                                background: "transparent",
                                color: isDark ? "#eee" : "#000",
                                cursor: "pointer"
                            }}
                        >
                            ×
                        </button>
                        <div style={{ paddingTop: 30 }}>
                            <h2>Game Information</h2>
                            <p>
                                Welcome to Bingo! Each tile represents a unique phrase. Click to cross off tiles.
                                Some tiles have special rules.
                            </p>
                            <p>
                                🏆 This is a challenge tile! You have to achieve this yourself without a parent suspecting anything
                            </p>
                            <p>
                                🗣️ Phrase tile - if a parent says this phrase, you can cross it off. Again, do it without them suspecting anything
                            </p>
                            <p>
                                🇬🇧 Brexit tile. No special rules here, just being patriotic
                            </p>
                            <p>
                                Try not to ruin Christmas, god bless.
                            </p>
                            <div style={{ marginTop: 30, textAlign: "center" }}>
                            <button
                                onClick={() => setShowInfo(false)}
                                style={{
                                    padding: "10px 18px",
                                    fontSize: 16,
                                    borderRadius: 6,
                                    border: "1px solid #666",
                                    backgroundColor: isDark ? "#2A2A2A" : "#f0f0f0",
                                    color: isDark ? "#eee" : "#000",
                                    cursor: "pointer"
                                }}
                            >
                                Thank you Michael
                            </button>
                        </div>
                        </div>
                    </div>
                </div>
            )}

        </div>
    );
}

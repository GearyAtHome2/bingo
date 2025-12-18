const PhraseTypes = {
    CHALLENGE: {
        background: "rgba(183, 28, 28, 0.05)",
        color: "#b71c1c",
        emoji: "🏆"
    },
    PATRIOT: {
        background: "rgba(30, 136, 229, 0.05)",
        color: "#1e88e5",
        emoji: "🇬🇧"
    },
    DECLARE: {
        background: "rgba(245, 124, 0, 0.05)",
        color: "#f57c00",
        emoji: "🗣️"
    },
    NORMAL: {
        background: "rgba(128, 128, 128, 0.05)",
        color: "#dddddd",
        emoji: null
    }
};


export function getPhraseStyle(phrase, isCrossed) {
    const typeInfo = PhraseTypes[phrase.type] || PhraseTypes.NORMAL;
    return {
        backgroundColor: isCrossed ? "#8BC34A" : typeInfo.background,
        color: isCrossed ? "white" : typeInfo.color,
        border: "1px solid #ccc",
        fontWeight: "normal",
        className: "",
        emoji: typeInfo.emoji
    };
}

export default PhraseTypes;

import axios from "axios";

const api = axios.create({
  baseURL: "https://bingo-dfl1.onrender.com",
  withCredentials: true
});

export default api;

// Optional helper functions
export const getCard = (email) =>
  api.get("/card", {
    params: { email }
  });

export const toggle = (email, index) =>
  api.post("/toggle", { email, index });

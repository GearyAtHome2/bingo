import axios from "axios";

const api = axios.create({
  baseURL: "http://192.168.0.33:8080",
  withCredentials: true//disabled for now until we set cookies on register/login
});

export default api;

// Optional helper functions
export const getCard = (email) =>
  api.get("/card", {
    params: { email }
  });

export const toggle = (email, index) =>
  api.post("/toggle", { email, index });

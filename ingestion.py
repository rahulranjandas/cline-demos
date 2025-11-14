from langchain_community.vectorstores import Chroma
from langchain_community.embeddings import OllamaEmbeddings
from langchain_core.documents import Document

# Sample data
documents = [
    "Reset your password using the forgot password link.",
    "You can update your payment details in the account settings.",
    "Shipping is free for all orders above $100 in the USA."
]

# Create embedding model using Ollama
embedding_model = OllamaEmbeddings(model="nomic-embed-text")  # make sure this model is downloaded in Ollama

# Convert to LangChain Document format
docs = [Document(page_content=doc) for doc in documents]

# Store in ChromaDB
db = Chroma.from_documents(docs, embedding_model, persist_directory="./chroma_store")

# Persist to disk
db.persist()

print("Documents stored in ChromaDB.")

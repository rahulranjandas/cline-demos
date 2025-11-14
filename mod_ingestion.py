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
embedding_model = OllamaEmbeddings(model="nomic-embed-text")

# Convert to LangChain Document format
docs = [Document(page_content=doc) for doc in documents]

# Create ChromaDB and store embeddings
db = Chroma.from_documents(
    documents=docs,
    embedding=embedding_model,
    persist_directory="./chroma_store"
)

# Persist to disk
db.persist()

print("Documents stored in ChromaDB.")

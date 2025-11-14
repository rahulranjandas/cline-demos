from langchain_community.vectorstores import Chroma
from langchain_ollama import OllamaEmbeddings, ChatOllama
from langchain_community.chains import RetrievalQA

# Load embedding model and vectorstore
embedding_model = OllamaEmbeddings(model="nomic-embed-text")
db = Chroma(
    persist_directory="./chroma_store",
    embedding_function=embedding_model
)

# Load Ollama chat model
llm = ChatOllama(model="qwen3:0.6b")  # or llama3.1, qwen2.5, mistral, etc.

# Create RetrievalQA chain
qa = RetrievalQA.from_chain_type(
    llm=llm,
    retriever=db.as_retriever(),
    return_source_documents=True
)

# Ask a question
query = "How can I reset my password?"
result = qa.invoke(query)

# Output answer
print("Answer:", result["result"])
print("\nSources:")
for doc in result["source_documents"]:
    print("-", doc.page_content)
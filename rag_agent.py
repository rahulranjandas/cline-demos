from langchain_community.vectorstores import Chroma
from langchain_community.embeddings import OllamaEmbeddings
from langchain.chains import RetrievalQA
from langchain.chat_models import ChatOllama

# Load embedding model and vectorstore
embedding_model = OllamaEmbeddings(model="nomic-embed-text")
db = Chroma(persist_directory="./chroma_store", embedding_function=embedding_model)

# Load Ollama language model
llm = ChatOllama(model="qwen3:0.6b")  # Replace with any Ollama-supported chat model

# Create RetrievalQA chain
qa = RetrievalQA.from_chain_type(llm=llm, retriever=db.as_retriever(), return_source_documents=True)

# Ask a question
query = "How can I reset my password?"
result = qa(query)

# Output answer and sources
print("Answer:", result['result'])
from neo4j import GraphDatabase

db = GraphDatabase.driver("bolt://localhost:7687", auth=("neo4j", "neoFourJ"))


def create_graph():
    graph_query = """
    MERGE (a:Place {id:"A"})
    MERGE (b:Place {id:"B"})
    MERGE (c:Place {id:"C"})
    MERGE (d:Place {id:"D"})
    MERGE (e:Place {id:"E"})
    MERGE (f:Place {id:"F"})
    
    MERGE (a)-[:LINK {cost:10}]->(b)
    MERGE (a)-[:LINK {cost:11}]->(c)
    MERGE (a)-[:LINK {cost:12}]->(d)
    MERGE (a)-[:LINK {cost:13}]->(e)
    MERGE (b)-[:LINK {cost:10}]->(a)
    MERGE (b)-[:LINK {cost:11}]->(c)
    MERGE (b)-[:LINK {cost:12}]->(d)
    MERGE (b)-[:LINK {cost:13}]->(e)
    MERGE (c)-[:LINK {cost:10}]->(a)
    MERGE (c)-[:LINK {cost:10}]->(b)
    MERGE (c)-[:LINK {cost:12}]->(d)
    MERGE (c)-[:LINK {cost:13}]->(e)
    MERGE (d)-[:LINK {cost:10}]->(a)
    MERGE (d)-[:LINK {cost:10}]->(b)
    MERGE (d)-[:LINK {cost:11}]->(c)
    MERGE (d)-[:LINK {cost:13}]->(e)
    MERGE (e)-[:LINK {cost:10}]->(a)
    MERGE (e)-[:LINK {cost:10}]->(b)
    MERGE (e)-[:LINK {cost:11}]->(c)
    MERGE (e)-[:LINK {cost:12}]->(d)
    """
    with db.session() as session:
        session.run(graph_query)


def print_relations(relation):
    rel_query = "MATCH ()-[r:" + relation + "]->() RETURN r"

    with db.session() as session:
        relationships = session.run(rel_query)

    print("\n\nRELATIONSHIPS:")
    for rel in relationships:
        print(rel)


def print_nodes(node):
    node_query = "MATCH (p:" + node + ") RETURN p"

    with db.session() as session:
        nodes = session.run(node_query)

    print("NODES:")
    for node in nodes:
        print(node)


def spanning_tree():
    query = """
        MATCH (n:Place {id:"D"})
        CALL algo.spanningTree.minimum("Place", "LINK", "cost", id(n),
        {write:true, writeProperty:"MINST"})
        YIELD loadMillis, computeMillis, writeMillis, effectiveNodeCount
        RETURN loadMillis, computeMillis, writeMillis, effectiveNodeCount;
        """

    with db.session() as session:
        session.run(query)


if __name__ == "__main__":
    create_graph()
    spanning_tree()
    print_nodes("Place")
    print_relations("LINK")
    print_relations("MINST")
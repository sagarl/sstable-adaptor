// $ANTLR 3.5.2 Parser.g 2017-07-12 09:08:49

package org.apache.cassandra.cql3;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.apache.cassandra.config.ColumnDefinition;
import org.apache.cassandra.cql3.statements.CFProperties;
import org.apache.cassandra.cql3.statements.CreateTableStatement;
import org.apache.cassandra.cql3.statements.ParsedStatement;
import org.apache.cassandra.cql3.statements.PropertyDefinitions;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.exceptions.SyntaxException;
import org.apache.cassandra.utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class Cql_Parser extends Parser {
	public static final int EOF=-1;
	public static final int T__182=182;
	public static final int T__183=183;
	public static final int T__184=184;
	public static final int T__185=185;
	public static final int T__186=186;
	public static final int T__187=187;
	public static final int T__188=188;
	public static final int T__189=189;
	public static final int T__190=190;
	public static final int T__191=191;
	public static final int T__192=192;
	public static final int T__193=193;
	public static final int T__194=194;
	public static final int T__195=195;
	public static final int T__196=196;
	public static final int T__197=197;
	public static final int T__198=198;
	public static final int T__199=199;
	public static final int T__200=200;
	public static final int T__201=201;
	public static final int T__202=202;
	public static final int T__203=203;
	public static final int A=4;
	public static final int B=5;
	public static final int BOOLEAN=6;
	public static final int C=7;
	public static final int COMMENT=8;
	public static final int D=9;
	public static final int DIGIT=10;
	public static final int DURATION=11;
	public static final int DURATION_UNIT=12;
	public static final int E=13;
	public static final int EXPONENT=14;
	public static final int F=15;
	public static final int FLOAT=16;
	public static final int G=17;
	public static final int H=18;
	public static final int HEX=19;
	public static final int HEXNUMBER=20;
	public static final int I=21;
	public static final int IDENT=22;
	public static final int INTEGER=23;
	public static final int J=24;
	public static final int K=25;
	public static final int K_ADD=26;
	public static final int K_AGGREGATE=27;
	public static final int K_ALL=28;
	public static final int K_ALLOW=29;
	public static final int K_ALTER=30;
	public static final int K_AND=31;
	public static final int K_APPLY=32;
	public static final int K_AS=33;
	public static final int K_ASC=34;
	public static final int K_ASCII=35;
	public static final int K_AUTHORIZE=36;
	public static final int K_BATCH=37;
	public static final int K_BEGIN=38;
	public static final int K_BIGINT=39;
	public static final int K_BLOB=40;
	public static final int K_BOOLEAN=41;
	public static final int K_BY=42;
	public static final int K_CALLED=43;
	public static final int K_CAST=44;
	public static final int K_CLUSTERING=45;
	public static final int K_COLUMNFAMILY=46;
	public static final int K_COMPACT=47;
	public static final int K_CONTAINS=48;
	public static final int K_COUNT=49;
	public static final int K_COUNTER=50;
	public static final int K_CREATE=51;
	public static final int K_CUSTOM=52;
	public static final int K_DATE=53;
	public static final int K_DECIMAL=54;
	public static final int K_DEFAULT=55;
	public static final int K_DELETE=56;
	public static final int K_DESC=57;
	public static final int K_DESCRIBE=58;
	public static final int K_DISTINCT=59;
	public static final int K_DOUBLE=60;
	public static final int K_DROP=61;
	public static final int K_DURATION=62;
	public static final int K_ENTRIES=63;
	public static final int K_EXECUTE=64;
	public static final int K_EXISTS=65;
	public static final int K_FILTERING=66;
	public static final int K_FINALFUNC=67;
	public static final int K_FLOAT=68;
	public static final int K_FROM=69;
	public static final int K_FROZEN=70;
	public static final int K_FULL=71;
	public static final int K_FUNCTION=72;
	public static final int K_FUNCTIONS=73;
	public static final int K_GRANT=74;
	public static final int K_GROUP=75;
	public static final int K_IF=76;
	public static final int K_IN=77;
	public static final int K_INDEX=78;
	public static final int K_INET=79;
	public static final int K_INFINITY=80;
	public static final int K_INITCOND=81;
	public static final int K_INPUT=82;
	public static final int K_INSERT=83;
	public static final int K_INT=84;
	public static final int K_INTO=85;
	public static final int K_IS=86;
	public static final int K_JSON=87;
	public static final int K_KEY=88;
	public static final int K_KEYS=89;
	public static final int K_KEYSPACE=90;
	public static final int K_KEYSPACES=91;
	public static final int K_LANGUAGE=92;
	public static final int K_LIKE=93;
	public static final int K_LIMIT=94;
	public static final int K_LIST=95;
	public static final int K_LOGIN=96;
	public static final int K_MAP=97;
	public static final int K_MATERIALIZED=98;
	public static final int K_MBEAN=99;
	public static final int K_MBEANS=100;
	public static final int K_MODIFY=101;
	public static final int K_NAN=102;
	public static final int K_NOLOGIN=103;
	public static final int K_NORECURSIVE=104;
	public static final int K_NOSUPERUSER=105;
	public static final int K_NOT=106;
	public static final int K_NULL=107;
	public static final int K_OF=108;
	public static final int K_ON=109;
	public static final int K_OPTIONS=110;
	public static final int K_OR=111;
	public static final int K_ORDER=112;
	public static final int K_PARTITION=113;
	public static final int K_PASSWORD=114;
	public static final int K_PER=115;
	public static final int K_PERMISSION=116;
	public static final int K_PERMISSIONS=117;
	public static final int K_PRIMARY=118;
	public static final int K_RENAME=119;
	public static final int K_REPLACE=120;
	public static final int K_RETURNS=121;
	public static final int K_REVOKE=122;
	public static final int K_ROLE=123;
	public static final int K_ROLES=124;
	public static final int K_SELECT=125;
	public static final int K_SET=126;
	public static final int K_SFUNC=127;
	public static final int K_SMALLINT=128;
	public static final int K_STATIC=129;
	public static final int K_STORAGE=130;
	public static final int K_STYPE=131;
	public static final int K_SUPERUSER=132;
	public static final int K_TEXT=133;
	public static final int K_TIME=134;
	public static final int K_TIMESTAMP=135;
	public static final int K_TIMEUUID=136;
	public static final int K_TINYINT=137;
	public static final int K_TO=138;
	public static final int K_TOKEN=139;
	public static final int K_TRIGGER=140;
	public static final int K_TRUNCATE=141;
	public static final int K_TTL=142;
	public static final int K_TUPLE=143;
	public static final int K_TYPE=144;
	public static final int K_UNLOGGED=145;
	public static final int K_UNSET=146;
	public static final int K_UPDATE=147;
	public static final int K_USE=148;
	public static final int K_USER=149;
	public static final int K_USERS=150;
	public static final int K_USING=151;
	public static final int K_UUID=152;
	public static final int K_VALUES=153;
	public static final int K_VARCHAR=154;
	public static final int K_VARINT=155;
	public static final int K_VIEW=156;
	public static final int K_WHERE=157;
	public static final int K_WITH=158;
	public static final int K_WRITETIME=159;
	public static final int L=160;
	public static final int LETTER=161;
	public static final int M=162;
	public static final int MULTILINE_COMMENT=163;
	public static final int N=164;
	public static final int O=165;
	public static final int P=166;
	public static final int Q=167;
	public static final int QMARK=168;
	public static final int QUOTED_NAME=169;
	public static final int R=170;
	public static final int S=171;
	public static final int STRING_LITERAL=172;
	public static final int T=173;
	public static final int U=174;
	public static final int UUID=175;
	public static final int V=176;
	public static final int W=177;
	public static final int WS=178;
	public static final int X=179;
	public static final int Y=180;
	public static final int Z=181;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators
	public CqlParser gCql;
	public CqlParser gParent;


	public Cql_Parser(TokenStream input, CqlParser gCql) {
		this(input, new RecognizerSharedState(), gCql);
	}
	public Cql_Parser(TokenStream input, RecognizerSharedState state, CqlParser gCql) {
		super(input, state);
		this.gCql = gCql;
		gParent = gCql;
	}

	@Override public String[] getTokenNames() { return CqlParser.tokenNames; }
	@Override public String getGrammarFileName() { return "Parser.g"; }


	private final List<ErrorListener> listeners = new ArrayList<ErrorListener>();
	protected final List<ColumnIdentifier> bindVariables = new ArrayList<ColumnIdentifier>();

	public static final Set<String> reservedTypeNames = new HashSet<String>()
	{{
		add("byte");
		add("complex");
		add("enum");
		add("date");
		add("interval");
		add("macaddr");
		add("bitstring");
	}};

	public AbstractMarker.Raw newBindVariables(ColumnIdentifier name)
	{
		AbstractMarker.Raw marker = new AbstractMarker.Raw(bindVariables.size());
		bindVariables.add(name);
		return marker;
	}

	public AbstractMarker.INRaw newINBindVariables(ColumnIdentifier name)
	{
		AbstractMarker.INRaw marker = new AbstractMarker.INRaw(bindVariables.size());
		bindVariables.add(name);
		return marker;
	}

	public Tuples.Raw newTupleBindVariables(ColumnIdentifier name)
	{
		Tuples.Raw marker = new Tuples.Raw(bindVariables.size());
		bindVariables.add(name);
		return marker;
	}

	public Tuples.INRaw newTupleINBindVariables(ColumnIdentifier name)
	{
		Tuples.INRaw marker = new Tuples.INRaw(bindVariables.size());
		bindVariables.add(name);
		return marker;
	}

	public Json.Marker newJsonBindVariables(ColumnIdentifier name)
	{
		Json.Marker marker = new Json.Marker(bindVariables.size());
		bindVariables.add(name);
		return marker;
	}

	public void addErrorListener(ErrorListener listener)
	{
		this.listeners.add(listener);
	}

	public void removeErrorListener(ErrorListener listener)
	{
		this.listeners.remove(listener);
	}

	public void displayRecognitionError(String[] tokenNames, RecognitionException e)
	{
		for (int i = 0, m = listeners.size(); i < m; i++)
			listeners.get(i).syntaxError(this, tokenNames, e);
	}

	protected void addRecognitionError(String msg)
	{
		for (int i = 0, m = listeners.size(); i < m; i++)
			listeners.get(i).syntaxError(this, msg);
	}

	public Map<String, String> convertPropertyMap(Maps.Literal map)
	{
		if (map == null || map.entries == null || map.entries.isEmpty())
			return Collections.<String, String>emptyMap();

		Map<String, String> res = new HashMap<>(map.entries.size());

		for (Pair<Term.Raw, Term.Raw> entry : map.entries)
		{
			// Because the parser tries to be smart and recover on error (to
			// allow displaying more than one error I suppose), we have null
			// entries in there. Just skip those, a proper error will be thrown in the end.
			if (entry.left == null || entry.right == null)
				break;

			if (!(entry.left instanceof Constants.Literal))
			{
				String msg = "Invalid property name: " + entry.left;
				if (entry.left instanceof AbstractMarker.Raw)
					msg += " (bind variables are not supported in DDL queries)";
				addRecognitionError(msg);
				break;
			}
			if (!(entry.right instanceof Constants.Literal))
			{
				String msg = "Invalid property value: " + entry.right + " for property: " + entry.left;
				if (entry.right instanceof AbstractMarker.Raw)
					msg += " (bind variables are not supported in DDL queries)";
				addRecognitionError(msg);
				break;
			}

			if (res.put(((Constants.Literal)entry.left).getRawText(), ((Constants.Literal)entry.right).getRawText()) != null)
			{
				addRecognitionError(String.format("Multiple definition for property " + ((Constants.Literal)entry.left).getRawText()));
			}
		}

		return res;
	}

	public void addRawUpdate(List<Pair<ColumnDefinition.Raw, Operation.RawUpdate>> operations, ColumnDefinition.Raw key, Operation.RawUpdate update)
	{
		for (Pair<ColumnDefinition.Raw, Operation.RawUpdate> p : operations)
		{
			if (p.left.equals(key) && !p.right.isCompatibleWith(update))
				addRecognitionError("Multiple incompatible setting of column " + key);
		}
		operations.add(Pair.create(key, update));
	}


	public String canonicalizeObjectName(String s, boolean enforcePattern)
	{
		// these two conditions are here because technically they are valid
		// ObjectNames, but we want to restrict their use without adding unnecessary
		// work to JMXResource construction as that also happens on hotter code paths
		if ("".equals(s))
			addRecognitionError("Empty JMX object name supplied");

		if ("*:*".equals(s))
			addRecognitionError("Please use ALL MBEANS instead of wildcard pattern");

		try
		{
			javax.management.ObjectName objectName = javax.management.ObjectName.getInstance(s);
			if (enforcePattern && !objectName.isPattern())
				addRecognitionError("Plural form used, but non-pattern JMX object name specified (" + s + ")");
			return objectName.getCanonicalName();
		}
		catch (javax.management.MalformedObjectNameException e)
		{
			addRecognitionError(s + " is not a valid JMX object name");
			return s;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Recovery methods are overridden to avoid wasting work on recovering from errors when the result will be
	// ignored anyway.
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException
	{
		throw new MismatchedTokenException(ttype, input);
	}

	@Override
	public void recover(IntStream input, RecognitionException re)
	{
		// Do nothing.
	}



	// $ANTLR start "cqlStatement"
	// Parser.g:207:1: cqlStatement returns [ParsedStatement stmt] : (st1= selectStatement |st2= insertStatement |st3= updateStatement |st4= batchStatement |st5= deleteStatement |st6= useStatement |st7= truncateStatement |st8= createKeyspaceStatement |st9= createTableStatement |st10= createIndexStatement |st11= dropKeyspaceStatement |st12= dropTableStatement |st13= dropIndexStatement |st14= alterTableStatement |st15= alterKeyspaceStatement |st16= grantPermissionsStatement |st17= revokePermissionsStatement |st18= listPermissionsStatement |st19= createUserStatement |st20= alterUserStatement |st21= dropUserStatement |st22= listUsersStatement |st23= createTriggerStatement |st24= dropTriggerStatement |st25= createTypeStatement |st26= alterTypeStatement |st27= dropTypeStatement |st28= createFunctionStatement |st29= dropFunctionStatement |st30= createAggregateStatement |st31= dropAggregateStatement |st32= createRoleStatement |st33= alterRoleStatement |st34= dropRoleStatement |st35= listRolesStatement |st36= grantRoleStatement |st37= revokeRoleStatement |st38= createMaterializedViewStatement |st39= dropMaterializedViewStatement |st40= alterMaterializedViewStatement );
	public final ParsedStatement cqlStatement() throws RecognitionException {
		ParsedStatement stmt = null;

		CreateTableStatement.RawStatement st9 =null;


		try {
			// Parser.g:209:5: (st1= selectStatement |st2= insertStatement |st3= updateStatement |st4= batchStatement |st5= deleteStatement |st6= useStatement |st7= truncateStatement |st8= createKeyspaceStatement |st9= createTableStatement |st10= createIndexStatement |st11= dropKeyspaceStatement |st12= dropTableStatement |st13= dropIndexStatement |st14= alterTableStatement |st15= alterKeyspaceStatement |st16= grantPermissionsStatement |st17= revokePermissionsStatement |st18= listPermissionsStatement |st19= createUserStatement |st20= alterUserStatement |st21= dropUserStatement |st22= listUsersStatement |st23= createTriggerStatement |st24= dropTriggerStatement |st25= createTypeStatement |st26= alterTypeStatement |st27= dropTypeStatement |st28= createFunctionStatement |st29= dropFunctionStatement |st30= createAggregateStatement |st31= dropAggregateStatement |st32= createRoleStatement |st33= alterRoleStatement |st34= dropRoleStatement |st35= listRolesStatement |st36= grantRoleStatement |st37= revokeRoleStatement |st38= createMaterializedViewStatement |st39= dropMaterializedViewStatement |st40= alterMaterializedViewStatement )
			int alt1=40;
			alt1 = dfa1.predict(input);
			switch (alt1) {

				case 9 :
					// Parser.g:217:7: st9= createTableStatement
				{
					pushFollow(FOLLOW_createTableStatement_in_cqlStatement285);
					st9=createTableStatement();
					state._fsp--;

					stmt = st9;
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return stmt;
	}
	// $ANTLR end "cqlStatement"



	// $ANTLR start "createTableStatement"
	// Parser.g:654:1: createTableStatement returns [CreateTableStatement.RawStatement expr] : K_CREATE K_COLUMNFAMILY ( K_IF K_NOT K_EXISTS )? cf= columnFamilyName cfamDefinition[expr] ;
	public final CreateTableStatement.RawStatement createTableStatement() throws RecognitionException {
		CreateTableStatement.RawStatement expr = null;


		CFName cf =null;

		boolean ifNotExists = false;
		try {
			// Parser.g:656:5: ( K_CREATE K_COLUMNFAMILY ( K_IF K_NOT K_EXISTS )? cf= columnFamilyName cfamDefinition[expr] )
			// Parser.g:656:7: K_CREATE K_COLUMNFAMILY ( K_IF K_NOT K_EXISTS )? cf= columnFamilyName cfamDefinition[expr]
			{
				match(input,K_CREATE,FOLLOW_K_CREATE_in_createTableStatement3883);
				match(input,K_COLUMNFAMILY,FOLLOW_K_COLUMNFAMILY_in_createTableStatement3885);
				// Parser.g:656:31: ( K_IF K_NOT K_EXISTS )?
				int alt70=2;
				int LA70_0 = input.LA(1);
				if ( (LA70_0==K_IF) ) {
					alt70=1;
				}
				switch (alt70) {
					case 1 :
						// Parser.g:656:32: K_IF K_NOT K_EXISTS
					{
						match(input,K_IF,FOLLOW_K_IF_in_createTableStatement3888);
						match(input,K_NOT,FOLLOW_K_NOT_in_createTableStatement3890);
						match(input,K_EXISTS,FOLLOW_K_EXISTS_in_createTableStatement3892);
						ifNotExists = true;
					}
					break;

				}

				pushFollow(FOLLOW_columnFamilyName_in_createTableStatement3907);
				cf=columnFamilyName();
				state._fsp--;

				expr = new CreateTableStatement.RawStatement(cf, ifNotExists);
				pushFollow(FOLLOW_cfamDefinition_in_createTableStatement3917);
				cfamDefinition(expr);
				state._fsp--;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return expr;
	}
	// $ANTLR end "createTableStatement"



	// $ANTLR start "cfamDefinition"
	// Parser.g:661:1: cfamDefinition[CreateTableStatement.RawStatement expr] : '(' cfamColumns[expr] ( ',' ( cfamColumns[expr] )? )* ')' ( K_WITH cfamProperty[expr.properties] ( K_AND cfamProperty[expr.properties] )* )? ;
	public final void cfamDefinition(CreateTableStatement.RawStatement expr) throws RecognitionException {
		try {
			// Parser.g:662:5: ( '(' cfamColumns[expr] ( ',' ( cfamColumns[expr] )? )* ')' ( K_WITH cfamProperty[expr.properties] ( K_AND cfamProperty[expr.properties] )* )? )
			// Parser.g:662:7: '(' cfamColumns[expr] ( ',' ( cfamColumns[expr] )? )* ')' ( K_WITH cfamProperty[expr.properties] ( K_AND cfamProperty[expr.properties] )* )?
			{
				match(input,183,FOLLOW_183_in_cfamDefinition3936);
				pushFollow(FOLLOW_cfamColumns_in_cfamDefinition3938);
				cfamColumns(expr);
				state._fsp--;

				// Parser.g:662:29: ( ',' ( cfamColumns[expr] )? )*
				loop72:
				while (true) {
					int alt72=2;
					int LA72_0 = input.LA(1);
					if ( (LA72_0==187) ) {
						alt72=1;
					}

					switch (alt72) {
						case 1 :
							// Parser.g:662:31: ',' ( cfamColumns[expr] )?
						{
							match(input,187,FOLLOW_187_in_cfamDefinition3943);
							// Parser.g:662:35: ( cfamColumns[expr] )?
							int alt71=2;
							int LA71_0 = input.LA(1);
							if ( (LA71_0==IDENT||(LA71_0 >= K_AGGREGATE && LA71_0 <= K_ALL)||LA71_0==K_AS||LA71_0==K_ASCII||(LA71_0 >= K_BIGINT && LA71_0 <= K_BOOLEAN)||(LA71_0 >= K_CALLED && LA71_0 <= K_CLUSTERING)||(LA71_0 >= K_COMPACT && LA71_0 <= K_COUNTER)||(LA71_0 >= K_CUSTOM && LA71_0 <= K_DECIMAL)||(LA71_0 >= K_DISTINCT && LA71_0 <= K_DOUBLE)||LA71_0==K_DURATION||(LA71_0 >= K_EXISTS && LA71_0 <= K_FLOAT)||LA71_0==K_FROZEN||(LA71_0 >= K_FUNCTION && LA71_0 <= K_FUNCTIONS)||LA71_0==K_GROUP||LA71_0==K_INET||(LA71_0 >= K_INITCOND && LA71_0 <= K_INPUT)||LA71_0==K_INT||(LA71_0 >= K_JSON && LA71_0 <= K_KEYS)||(LA71_0 >= K_KEYSPACES && LA71_0 <= K_LIKE)||(LA71_0 >= K_LIST && LA71_0 <= K_MAP)||LA71_0==K_NOLOGIN||LA71_0==K_NOSUPERUSER||LA71_0==K_OPTIONS||(LA71_0 >= K_PARTITION && LA71_0 <= K_PRIMARY)||LA71_0==K_RETURNS||(LA71_0 >= K_ROLE && LA71_0 <= K_ROLES)||(LA71_0 >= K_SFUNC && LA71_0 <= K_TINYINT)||LA71_0==K_TRIGGER||(LA71_0 >= K_TTL && LA71_0 <= K_TYPE)||(LA71_0 >= K_USER && LA71_0 <= K_USERS)||(LA71_0 >= K_UUID && LA71_0 <= K_VARINT)||LA71_0==K_WRITETIME||LA71_0==QUOTED_NAME) ) {
								alt71=1;
							}
							switch (alt71) {
								case 1 :
									// Parser.g:662:35: cfamColumns[expr]
								{
									pushFollow(FOLLOW_cfamColumns_in_cfamDefinition3945);
									cfamColumns(expr);
									state._fsp--;

								}
								break;

							}

						}
						break;

						default :
							break loop72;
					}
				}

				match(input,184,FOLLOW_184_in_cfamDefinition3952);
				// Parser.g:663:7: ( K_WITH cfamProperty[expr.properties] ( K_AND cfamProperty[expr.properties] )* )?
				int alt74=2;
				int LA74_0 = input.LA(1);
				if ( (LA74_0==K_WITH) ) {
					alt74=1;
				}
				switch (alt74) {
					case 1 :
						// Parser.g:663:9: K_WITH cfamProperty[expr.properties] ( K_AND cfamProperty[expr.properties] )*
					{
						match(input,K_WITH,FOLLOW_K_WITH_in_cfamDefinition3962);
						pushFollow(FOLLOW_cfamProperty_in_cfamDefinition3964);
						cfamProperty(expr.properties);
						state._fsp--;

						// Parser.g:663:46: ( K_AND cfamProperty[expr.properties] )*
						loop73:
						while (true) {
							int alt73=2;
							int LA73_0 = input.LA(1);
							if ( (LA73_0==K_AND) ) {
								alt73=1;
							}

							switch (alt73) {
								case 1 :
									// Parser.g:663:48: K_AND cfamProperty[expr.properties]
								{
									match(input,K_AND,FOLLOW_K_AND_in_cfamDefinition3969);
									pushFollow(FOLLOW_cfamProperty_in_cfamDefinition3971);
									cfamProperty(expr.properties);
									state._fsp--;

								}
								break;

								default :
									break loop73;
							}
						}

					}
					break;

				}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cfamDefinition"



	// $ANTLR start "cfamColumns"
	// Parser.g:666:1: cfamColumns[CreateTableStatement.RawStatement expr] : (k= ident v= comparatorType ( K_STATIC )? ( K_PRIMARY K_KEY )? | K_PRIMARY K_KEY '(' pkDef[expr] ( ',' c= ident )* ')' );
	public final void cfamColumns(CreateTableStatement.RawStatement expr) throws RecognitionException {
		ColumnIdentifier k =null;
		CQL3Type.Raw v =null;
		ColumnIdentifier c =null;

		try {
			// Parser.g:667:5: (k= ident v= comparatorType ( K_STATIC )? ( K_PRIMARY K_KEY )? | K_PRIMARY K_KEY '(' pkDef[expr] ( ',' c= ident )* ')' )
			int alt78=2;
			int LA78_0 = input.LA(1);
			if ( (LA78_0==IDENT||(LA78_0 >= K_AGGREGATE && LA78_0 <= K_ALL)||LA78_0==K_AS||LA78_0==K_ASCII||(LA78_0 >= K_BIGINT && LA78_0 <= K_BOOLEAN)||(LA78_0 >= K_CALLED && LA78_0 <= K_CLUSTERING)||(LA78_0 >= K_COMPACT && LA78_0 <= K_COUNTER)||(LA78_0 >= K_CUSTOM && LA78_0 <= K_DECIMAL)||(LA78_0 >= K_DISTINCT && LA78_0 <= K_DOUBLE)||LA78_0==K_DURATION||(LA78_0 >= K_EXISTS && LA78_0 <= K_FLOAT)||LA78_0==K_FROZEN||(LA78_0 >= K_FUNCTION && LA78_0 <= K_FUNCTIONS)||LA78_0==K_GROUP||LA78_0==K_INET||(LA78_0 >= K_INITCOND && LA78_0 <= K_INPUT)||LA78_0==K_INT||(LA78_0 >= K_JSON && LA78_0 <= K_KEYS)||(LA78_0 >= K_KEYSPACES && LA78_0 <= K_LIKE)||(LA78_0 >= K_LIST && LA78_0 <= K_MAP)||LA78_0==K_NOLOGIN||LA78_0==K_NOSUPERUSER||LA78_0==K_OPTIONS||(LA78_0 >= K_PARTITION && LA78_0 <= K_PERMISSIONS)||LA78_0==K_RETURNS||(LA78_0 >= K_ROLE && LA78_0 <= K_ROLES)||(LA78_0 >= K_SFUNC && LA78_0 <= K_TINYINT)||LA78_0==K_TRIGGER||(LA78_0 >= K_TTL && LA78_0 <= K_TYPE)||(LA78_0 >= K_USER && LA78_0 <= K_USERS)||(LA78_0 >= K_UUID && LA78_0 <= K_VARINT)||LA78_0==K_WRITETIME||LA78_0==QUOTED_NAME) ) {
				alt78=1;
			}
			else if ( (LA78_0==K_PRIMARY) ) {
				alt78=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 78, 0, input);
				throw nvae;
			}

			switch (alt78) {
				case 1 :
					// Parser.g:667:7: k= ident v= comparatorType ( K_STATIC )? ( K_PRIMARY K_KEY )?
				{
					pushFollow(FOLLOW_ident_in_cfamColumns3997);
					k=ident();
					state._fsp--;

					pushFollow(FOLLOW_comparatorType_in_cfamColumns4001);
					v=comparatorType();
					state._fsp--;

					boolean isStatic=false;
					// Parser.g:667:60: ( K_STATIC )?
					int alt75=2;
					int LA75_0 = input.LA(1);
					if ( (LA75_0==K_STATIC) ) {
						alt75=1;
					}
					switch (alt75) {
						case 1 :
							// Parser.g:667:61: K_STATIC
						{
							match(input,K_STATIC,FOLLOW_K_STATIC_in_cfamColumns4006);
							isStatic = true;
						}
						break;

					}

					expr.addDefinition(k, v, isStatic);
					// Parser.g:668:9: ( K_PRIMARY K_KEY )?
					int alt76=2;
					int LA76_0 = input.LA(1);
					if ( (LA76_0==K_PRIMARY) ) {
						alt76=1;
					}
					switch (alt76) {
						case 1 :
							// Parser.g:668:10: K_PRIMARY K_KEY
						{
							match(input,K_PRIMARY,FOLLOW_K_PRIMARY_in_cfamColumns4023);
							match(input,K_KEY,FOLLOW_K_KEY_in_cfamColumns4025);
							expr.addKeyAliases(Collections.singletonList(k));
						}
						break;

					}

				}
				break;
				case 2 :
					// Parser.g:669:7: K_PRIMARY K_KEY '(' pkDef[expr] ( ',' c= ident )* ')'
				{
					match(input,K_PRIMARY,FOLLOW_K_PRIMARY_in_cfamColumns4037);
					match(input,K_KEY,FOLLOW_K_KEY_in_cfamColumns4039);
					match(input,183,FOLLOW_183_in_cfamColumns4041);
					pushFollow(FOLLOW_pkDef_in_cfamColumns4043);
					pkDef(expr);
					state._fsp--;

					// Parser.g:669:39: ( ',' c= ident )*
					loop77:
					while (true) {
						int alt77=2;
						int LA77_0 = input.LA(1);
						if ( (LA77_0==187) ) {
							alt77=1;
						}

						switch (alt77) {
							case 1 :
								// Parser.g:669:40: ',' c= ident
							{
								match(input,187,FOLLOW_187_in_cfamColumns4047);
								pushFollow(FOLLOW_ident_in_cfamColumns4051);
								c=ident();
								state._fsp--;

								expr.addColumnAlias(c);
							}
							break;

							default :
								break loop77;
						}
					}

					match(input,184,FOLLOW_184_in_cfamColumns4058);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cfamColumns"



	// $ANTLR start "pkDef"
	// Parser.g:672:1: pkDef[CreateTableStatement.RawStatement expr] : (k= ident | '(' k1= ident ( ',' kn= ident )* ')' );
	public final void pkDef(CreateTableStatement.RawStatement expr) throws RecognitionException {
		ColumnIdentifier k =null;
		ColumnIdentifier k1 =null;
		ColumnIdentifier kn =null;

		try {
			// Parser.g:673:5: (k= ident | '(' k1= ident ( ',' kn= ident )* ')' )
			int alt80=2;
			int LA80_0 = input.LA(1);
			if ( (LA80_0==IDENT||(LA80_0 >= K_AGGREGATE && LA80_0 <= K_ALL)||LA80_0==K_AS||LA80_0==K_ASCII||(LA80_0 >= K_BIGINT && LA80_0 <= K_BOOLEAN)||(LA80_0 >= K_CALLED && LA80_0 <= K_CLUSTERING)||(LA80_0 >= K_COMPACT && LA80_0 <= K_COUNTER)||(LA80_0 >= K_CUSTOM && LA80_0 <= K_DECIMAL)||(LA80_0 >= K_DISTINCT && LA80_0 <= K_DOUBLE)||LA80_0==K_DURATION||(LA80_0 >= K_EXISTS && LA80_0 <= K_FLOAT)||LA80_0==K_FROZEN||(LA80_0 >= K_FUNCTION && LA80_0 <= K_FUNCTIONS)||LA80_0==K_GROUP||LA80_0==K_INET||(LA80_0 >= K_INITCOND && LA80_0 <= K_INPUT)||LA80_0==K_INT||(LA80_0 >= K_JSON && LA80_0 <= K_KEYS)||(LA80_0 >= K_KEYSPACES && LA80_0 <= K_LIKE)||(LA80_0 >= K_LIST && LA80_0 <= K_MAP)||LA80_0==K_NOLOGIN||LA80_0==K_NOSUPERUSER||LA80_0==K_OPTIONS||(LA80_0 >= K_PARTITION && LA80_0 <= K_PERMISSIONS)||LA80_0==K_RETURNS||(LA80_0 >= K_ROLE && LA80_0 <= K_ROLES)||(LA80_0 >= K_SFUNC && LA80_0 <= K_TINYINT)||LA80_0==K_TRIGGER||(LA80_0 >= K_TTL && LA80_0 <= K_TYPE)||(LA80_0 >= K_USER && LA80_0 <= K_USERS)||(LA80_0 >= K_UUID && LA80_0 <= K_VARINT)||LA80_0==K_WRITETIME||LA80_0==QUOTED_NAME) ) {
				alt80=1;
			}
			else if ( (LA80_0==183) ) {
				alt80=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 80, 0, input);
				throw nvae;
			}

			switch (alt80) {
				case 1 :
					// Parser.g:673:7: k= ident
				{
					pushFollow(FOLLOW_ident_in_pkDef4078);
					k=ident();
					state._fsp--;

					expr.addKeyAliases(Collections.singletonList(k));
				}
				break;
				case 2 :
					// Parser.g:674:7: '(' k1= ident ( ',' kn= ident )* ')'
				{
					match(input,183,FOLLOW_183_in_pkDef4088);
					List<ColumnIdentifier> l = new ArrayList<ColumnIdentifier>();
					pushFollow(FOLLOW_ident_in_pkDef4094);
					k1=ident();
					state._fsp--;

					l.add(k1);
					// Parser.g:674:101: ( ',' kn= ident )*
					loop79:
					while (true) {
						int alt79=2;
						int LA79_0 = input.LA(1);
						if ( (LA79_0==187) ) {
							alt79=1;
						}

						switch (alt79) {
							case 1 :
								// Parser.g:674:103: ',' kn= ident
							{
								match(input,187,FOLLOW_187_in_pkDef4100);
								pushFollow(FOLLOW_ident_in_pkDef4104);
								kn=ident();
								state._fsp--;

								l.add(kn);
							}
							break;

							default :
								break loop79;
						}
					}

					match(input,184,FOLLOW_184_in_pkDef4111);
					expr.addKeyAliases(l);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "pkDef"



	// $ANTLR start "cfamProperty"
	// Parser.g:677:1: cfamProperty[CFProperties props] : ( property[props.properties] | K_COMPACT K_STORAGE | K_CLUSTERING K_ORDER K_BY '(' cfamOrdering[props] ( ',' cfamOrdering[props] )* ')' );
	public final void cfamProperty(CFProperties props) throws RecognitionException {
		try {
			// Parser.g:678:5: ( property[props.properties] | K_COMPACT K_STORAGE | K_CLUSTERING K_ORDER K_BY '(' cfamOrdering[props] ( ',' cfamOrdering[props] )* ')' )
			int alt82=3;
			switch ( input.LA(1) ) {
				case IDENT:
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_ASCII:
				case K_BIGINT:
				case K_BLOB:
				case K_BOOLEAN:
				case K_CALLED:
				case K_CAST:
				case K_CONTAINS:
				case K_COUNT:
				case K_COUNTER:
				case K_CUSTOM:
				case K_DATE:
				case K_DECIMAL:
				case K_DISTINCT:
				case K_DOUBLE:
				case K_DURATION:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FLOAT:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INET:
				case K_INITCOND:
				case K_INPUT:
				case K_INT:
				case K_JSON:
				case K_KEY:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_SMALLINT:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TEXT:
				case K_TIME:
				case K_TIMESTAMP:
				case K_TIMEUUID:
				case K_TINYINT:
				case K_TRIGGER:
				case K_TTL:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_UUID:
				case K_VALUES:
				case K_VARCHAR:
				case K_VARINT:
				case K_WRITETIME:
				case QUOTED_NAME:
				{
					alt82=1;
				}
				break;
				case K_COMPACT:
				{
					int LA82_2 = input.LA(2);
					if ( (LA82_2==K_STORAGE) ) {
						alt82=2;
					}
					else if ( (LA82_2==195) ) {
						alt82=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							input.consume();
							NoViableAltException nvae =
									new NoViableAltException("", 82, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				break;
				case K_CLUSTERING:
				{
					int LA82_3 = input.LA(2);
					if ( (LA82_3==K_ORDER) ) {
						alt82=3;
					}
					else if ( (LA82_3==195) ) {
						alt82=1;
					}

					else {
						int nvaeMark = input.mark();
						try {
							input.consume();
							NoViableAltException nvae =
									new NoViableAltException("", 82, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 82, 0, input);
					throw nvae;
			}
			switch (alt82) {
				case 1 :
					// Parser.g:678:7: property[props.properties]
				{
					pushFollow(FOLLOW_property_in_cfamProperty4131);
					property(props.properties);
					state._fsp--;

				}
				break;
				case 2 :
					// Parser.g:679:7: K_COMPACT K_STORAGE
				{
					match(input,K_COMPACT,FOLLOW_K_COMPACT_in_cfamProperty4140);
					match(input,K_STORAGE,FOLLOW_K_STORAGE_in_cfamProperty4142);
					props.setCompactStorage();
				}
				break;
				case 3 :
					// Parser.g:680:7: K_CLUSTERING K_ORDER K_BY '(' cfamOrdering[props] ( ',' cfamOrdering[props] )* ')'
				{
					match(input,K_CLUSTERING,FOLLOW_K_CLUSTERING_in_cfamProperty4152);
					match(input,K_ORDER,FOLLOW_K_ORDER_in_cfamProperty4154);
					match(input,K_BY,FOLLOW_K_BY_in_cfamProperty4156);
					match(input,183,FOLLOW_183_in_cfamProperty4158);
					pushFollow(FOLLOW_cfamOrdering_in_cfamProperty4160);
					cfamOrdering(props);
					state._fsp--;

					// Parser.g:680:57: ( ',' cfamOrdering[props] )*
					loop81:
					while (true) {
						int alt81=2;
						int LA81_0 = input.LA(1);
						if ( (LA81_0==187) ) {
							alt81=1;
						}

						switch (alt81) {
							case 1 :
								// Parser.g:680:58: ',' cfamOrdering[props]
							{
								match(input,187,FOLLOW_187_in_cfamProperty4164);
								pushFollow(FOLLOW_cfamOrdering_in_cfamProperty4166);
								cfamOrdering(props);
								state._fsp--;

							}
							break;

							default :
								break loop81;
						}
					}

					match(input,184,FOLLOW_184_in_cfamProperty4171);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cfamProperty"



	// $ANTLR start "cfamOrdering"
	// Parser.g:683:1: cfamOrdering[CFProperties props] : k= ident ( K_ASC | K_DESC ) ;
	public final void cfamOrdering(CFProperties props) throws RecognitionException {
		ColumnIdentifier k =null;

		boolean reversed=false;
		try {
			// Parser.g:685:5: (k= ident ( K_ASC | K_DESC ) )
			// Parser.g:685:7: k= ident ( K_ASC | K_DESC )
			{
				pushFollow(FOLLOW_ident_in_cfamOrdering4199);
				k=ident();
				state._fsp--;

				// Parser.g:685:15: ( K_ASC | K_DESC )
				int alt83=2;
				int LA83_0 = input.LA(1);
				if ( (LA83_0==K_ASC) ) {
					alt83=1;
				}
				else if ( (LA83_0==K_DESC) ) {
					alt83=2;
				}

				else {
					NoViableAltException nvae =
							new NoViableAltException("", 83, 0, input);
					throw nvae;
				}

				switch (alt83) {
					case 1 :
						// Parser.g:685:16: K_ASC
					{
						match(input,K_ASC,FOLLOW_K_ASC_in_cfamOrdering4202);
					}
					break;
					case 2 :
						// Parser.g:685:24: K_DESC
					{
						match(input,K_DESC,FOLLOW_K_DESC_in_cfamOrdering4206);
						reversed=true;
					}
					break;

				}

				props.setOrdering(k, reversed);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cfamOrdering"





	// $ANTLR start "cfisStatic"
	// Parser.g:826:1: cfisStatic returns [boolean isStaticColumn] : ( K_STATIC )? ;
	public final boolean cfisStatic() throws RecognitionException {
		boolean isStaticColumn = false;



		boolean isStatic = false;

		try {
			// Parser.g:830:5: ( ( K_STATIC )? )
			// Parser.g:830:7: ( K_STATIC )?
			{
				// Parser.g:830:7: ( K_STATIC )?
				int alt112=2;
				int LA112_0 = input.LA(1);
				if ( (LA112_0==K_STATIC) ) {
					alt112=1;
				}
				switch (alt112) {
					case 1 :
						// Parser.g:830:8: K_STATIC
					{
						match(input,K_STATIC,FOLLOW_K_STATIC_in_cfisStatic5561);
						isStatic=true;
					}
					break;

				}

				isStaticColumn = isStatic;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return isStaticColumn;
	}
	// $ANTLR end "cfisStatic"





	// $ANTLR start "cident"
	// Parser.g:1176:1: cident returns [ColumnDefinition.Raw id] : (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword );
	public final ColumnDefinition.Raw cident() throws RecognitionException {
		ColumnDefinition.Raw id = null;


		Token t=null;
		String k =null;

		try {
			// Parser.g:1177:5: (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword )
			int alt149=3;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt149=1;
				}
				break;
				case QUOTED_NAME:
				{
					alt149=2;
				}
				break;
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_ASCII:
				case K_BIGINT:
				case K_BLOB:
				case K_BOOLEAN:
				case K_CALLED:
				case K_CAST:
				case K_CLUSTERING:
				case K_COMPACT:
				case K_CONTAINS:
				case K_COUNT:
				case K_COUNTER:
				case K_CUSTOM:
				case K_DATE:
				case K_DECIMAL:
				case K_DISTINCT:
				case K_DOUBLE:
				case K_DURATION:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FLOAT:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INET:
				case K_INITCOND:
				case K_INPUT:
				case K_INT:
				case K_JSON:
				case K_KEY:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_SMALLINT:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TEXT:
				case K_TIME:
				case K_TIMESTAMP:
				case K_TIMEUUID:
				case K_TINYINT:
				case K_TRIGGER:
				case K_TTL:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_UUID:
				case K_VALUES:
				case K_VARCHAR:
				case K_VARINT:
				case K_WRITETIME:
				{
					alt149=3;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 149, 0, input);
					throw nvae;
			}
			switch (alt149) {
				case 1 :
					// Parser.g:1177:7: t= IDENT
				{
					t=(Token)match(input,IDENT,FOLLOW_IDENT_in_cident7757);
					id = ColumnDefinition.Raw.forUnquoted((t!=null?t.getText():null));
				}
				break;
				case 2 :
					// Parser.g:1178:7: t= QUOTED_NAME
				{
					t=(Token)match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_cident7782);
					id = ColumnDefinition.Raw.forQuoted((t!=null?t.getText():null));
				}
				break;
				case 3 :
					// Parser.g:1179:7: k= unreserved_keyword
				{
					pushFollow(FOLLOW_unreserved_keyword_in_cident7801);
					k=unreserved_keyword();
					state._fsp--;

					id = ColumnDefinition.Raw.forUnquoted(k);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return id;
	}
	// $ANTLR end "cident"



	// $ANTLR start "ident"
	// Parser.g:1183:1: ident returns [ColumnIdentifier id] : (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword );
	public final ColumnIdentifier ident() throws RecognitionException {
		ColumnIdentifier id = null;


		Token t=null;
		String k =null;

		try {
			// Parser.g:1184:5: (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword )
			int alt150=3;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt150=1;
				}
				break;
				case QUOTED_NAME:
				{
					alt150=2;
				}
				break;
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_ASCII:
				case K_BIGINT:
				case K_BLOB:
				case K_BOOLEAN:
				case K_CALLED:
				case K_CAST:
				case K_CLUSTERING:
				case K_COMPACT:
				case K_CONTAINS:
				case K_COUNT:
				case K_COUNTER:
				case K_CUSTOM:
				case K_DATE:
				case K_DECIMAL:
				case K_DISTINCT:
				case K_DOUBLE:
				case K_DURATION:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FLOAT:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INET:
				case K_INITCOND:
				case K_INPUT:
				case K_INT:
				case K_JSON:
				case K_KEY:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_SMALLINT:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TEXT:
				case K_TIME:
				case K_TIMESTAMP:
				case K_TIMEUUID:
				case K_TINYINT:
				case K_TRIGGER:
				case K_TTL:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_UUID:
				case K_VALUES:
				case K_VARCHAR:
				case K_VARINT:
				case K_WRITETIME:
				{
					alt150=3;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 150, 0, input);
					throw nvae;
			}
			switch (alt150) {
				case 1 :
					// Parser.g:1184:7: t= IDENT
				{
					t=(Token)match(input,IDENT,FOLLOW_IDENT_in_ident7827);
					id = ColumnIdentifier.getInterned((t!=null?t.getText():null), false);
				}
				break;
				case 2 :
					// Parser.g:1185:7: t= QUOTED_NAME
				{
					t=(Token)match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_ident7852);
					id = ColumnIdentifier.getInterned((t!=null?t.getText():null), true);
				}
				break;
				case 3 :
					// Parser.g:1186:7: k= unreserved_keyword
				{
					pushFollow(FOLLOW_unreserved_keyword_in_ident7871);
					k=unreserved_keyword();
					state._fsp--;

					id = ColumnIdentifier.getInterned(k, false);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return id;
	}
	// $ANTLR end "ident"



	// $ANTLR start "fident"
	// Parser.g:1189:1: fident returns [FieldIdentifier id] : (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword );
	public final FieldIdentifier fident() throws RecognitionException {
		FieldIdentifier id = null;


		Token t=null;
		String k =null;

		try {
			// Parser.g:1190:5: (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword )
			int alt151=3;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt151=1;
				}
				break;
				case QUOTED_NAME:
				{
					alt151=2;
				}
				break;
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_ASCII:
				case K_BIGINT:
				case K_BLOB:
				case K_BOOLEAN:
				case K_CALLED:
				case K_CAST:
				case K_CLUSTERING:
				case K_COMPACT:
				case K_CONTAINS:
				case K_COUNT:
				case K_COUNTER:
				case K_CUSTOM:
				case K_DATE:
				case K_DECIMAL:
				case K_DISTINCT:
				case K_DOUBLE:
				case K_DURATION:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FLOAT:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INET:
				case K_INITCOND:
				case K_INPUT:
				case K_INT:
				case K_JSON:
				case K_KEY:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_SMALLINT:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TEXT:
				case K_TIME:
				case K_TIMESTAMP:
				case K_TIMEUUID:
				case K_TINYINT:
				case K_TRIGGER:
				case K_TTL:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_UUID:
				case K_VALUES:
				case K_VARCHAR:
				case K_VARINT:
				case K_WRITETIME:
				{
					alt151=3;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 151, 0, input);
					throw nvae;
			}
			switch (alt151) {
				case 1 :
					// Parser.g:1190:7: t= IDENT
				{
					t=(Token)match(input,IDENT,FOLLOW_IDENT_in_fident7896);
					id = FieldIdentifier.forUnquoted((t!=null?t.getText():null));
				}
				break;
				case 2 :
					// Parser.g:1191:7: t= QUOTED_NAME
				{
					t=(Token)match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_fident7921);
					id = FieldIdentifier.forQuoted((t!=null?t.getText():null));
				}
				break;
				case 3 :
					// Parser.g:1192:7: k= unreserved_keyword
				{
					pushFollow(FOLLOW_unreserved_keyword_in_fident7940);
					k=unreserved_keyword();
					state._fsp--;

					id = FieldIdentifier.forUnquoted(k);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return id;
	}
	// $ANTLR end "fident"



	// $ANTLR start "noncol_ident"
	// Parser.g:1196:1: noncol_ident returns [ColumnIdentifier id] : (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword );
	public final ColumnIdentifier noncol_ident() throws RecognitionException {
		ColumnIdentifier id = null;


		Token t=null;
		String k =null;

		try {
			// Parser.g:1197:5: (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword )
			int alt152=3;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt152=1;
				}
				break;
				case QUOTED_NAME:
				{
					alt152=2;
				}
				break;
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_ASCII:
				case K_BIGINT:
				case K_BLOB:
				case K_BOOLEAN:
				case K_CALLED:
				case K_CAST:
				case K_CLUSTERING:
				case K_COMPACT:
				case K_CONTAINS:
				case K_COUNT:
				case K_COUNTER:
				case K_CUSTOM:
				case K_DATE:
				case K_DECIMAL:
				case K_DISTINCT:
				case K_DOUBLE:
				case K_DURATION:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FLOAT:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INET:
				case K_INITCOND:
				case K_INPUT:
				case K_INT:
				case K_JSON:
				case K_KEY:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_SMALLINT:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TEXT:
				case K_TIME:
				case K_TIMESTAMP:
				case K_TIMEUUID:
				case K_TINYINT:
				case K_TRIGGER:
				case K_TTL:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_UUID:
				case K_VALUES:
				case K_VARCHAR:
				case K_VARINT:
				case K_WRITETIME:
				{
					alt152=3;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 152, 0, input);
					throw nvae;
			}
			switch (alt152) {
				case 1 :
					// Parser.g:1197:7: t= IDENT
				{
					t=(Token)match(input,IDENT,FOLLOW_IDENT_in_noncol_ident7966);
					id = new ColumnIdentifier((t!=null?t.getText():null), false);
				}
				break;
				case 2 :
					// Parser.g:1198:7: t= QUOTED_NAME
				{
					t=(Token)match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_noncol_ident7991);
					id = new ColumnIdentifier((t!=null?t.getText():null), true);
				}
				break;
				case 3 :
					// Parser.g:1199:7: k= unreserved_keyword
				{
					pushFollow(FOLLOW_unreserved_keyword_in_noncol_ident8010);
					k=unreserved_keyword();
					state._fsp--;

					id = new ColumnIdentifier(k, false);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return id;
	}
	// $ANTLR end "noncol_ident"



	// $ANTLR start "keyspaceName"
	// Parser.g:1203:1: keyspaceName returns [String id] : ksName[name] ;
	public final String keyspaceName() throws RecognitionException {
		String id = null;


		CFName name = new CFName();
		try {
			// Parser.g:1205:5: ( ksName[name] )
			// Parser.g:1205:7: ksName[name]
			{
				pushFollow(FOLLOW_ksName_in_keyspaceName8043);
				ksName(name);
				state._fsp--;

				id = name.getKeyspace();
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return id;
	}
	// $ANTLR end "keyspaceName"




	// $ANTLR start "columnFamilyName"
	// Parser.g:1213:1: columnFamilyName returns [CFName name] : ( ksName[name] '.' )? cfName[name] ;
	public final CFName columnFamilyName() throws RecognitionException {
		CFName name = null;


		name = new CFName();
		try {
			// Parser.g:1215:5: ( ( ksName[name] '.' )? cfName[name] )
			// Parser.g:1215:7: ( ksName[name] '.' )? cfName[name]
			{
				// Parser.g:1215:7: ( ksName[name] '.' )?
				int alt154=2;
				alt154 = dfa154.predict(input);
				switch (alt154) {
					case 1 :
						// Parser.g:1215:8: ksName[name] '.'
					{
						pushFollow(FOLLOW_ksName_in_columnFamilyName8116);
						ksName(name);
						state._fsp--;

						match(input,190,FOLLOW_190_in_columnFamilyName8119);
					}
					break;

				}

				pushFollow(FOLLOW_cfName_in_columnFamilyName8123);
				cfName(name);
				state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return name;
	}
	// $ANTLR end "columnFamilyName"




	// $ANTLR start "ksName"
	// Parser.g:1227:1: ksName[KeyspaceElementName name] : (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword | QMARK );
	public final void ksName(KeyspaceElementName name) throws RecognitionException {
		Token t=null;
		String k =null;

		try {
			// Parser.g:1228:5: (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword | QMARK )
			int alt156=4;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt156=1;
				}
				break;
				case QUOTED_NAME:
				{
					alt156=2;
				}
				break;
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_ASCII:
				case K_BIGINT:
				case K_BLOB:
				case K_BOOLEAN:
				case K_CALLED:
				case K_CAST:
				case K_CLUSTERING:
				case K_COMPACT:
				case K_CONTAINS:
				case K_COUNT:
				case K_COUNTER:
				case K_CUSTOM:
				case K_DATE:
				case K_DECIMAL:
				case K_DISTINCT:
				case K_DOUBLE:
				case K_DURATION:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FLOAT:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INET:
				case K_INITCOND:
				case K_INPUT:
				case K_INT:
				case K_JSON:
				case K_KEY:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_SMALLINT:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TEXT:
				case K_TIME:
				case K_TIMESTAMP:
				case K_TIMEUUID:
				case K_TINYINT:
				case K_TRIGGER:
				case K_TTL:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_UUID:
				case K_VALUES:
				case K_VARCHAR:
				case K_VARINT:
				case K_WRITETIME:
				{
					alt156=3;
				}
				break;
				case QMARK:
				{
					alt156=4;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 156, 0, input);
					throw nvae;
			}
			switch (alt156) {
				case 1 :
					// Parser.g:1228:7: t= IDENT
				{
					t=(Token)match(input,IDENT,FOLLOW_IDENT_in_ksName8211);
					name.setKeyspace((t!=null?t.getText():null), false);
				}
				break;
				case 2 :
					// Parser.g:1229:7: t= QUOTED_NAME
				{
					t=(Token)match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_ksName8236);
					name.setKeyspace((t!=null?t.getText():null), true);
				}
				break;
				case 3 :
					// Parser.g:1230:7: k= unreserved_keyword
				{
					pushFollow(FOLLOW_unreserved_keyword_in_ksName8255);
					k=unreserved_keyword();
					state._fsp--;

					name.setKeyspace(k, false);
				}
				break;
				case 4 :
					// Parser.g:1231:7: QMARK
				{
					match(input,QMARK,FOLLOW_QMARK_in_ksName8265);
					addRecognitionError("Bind variables cannot be used for keyspace names");
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ksName"



	// $ANTLR start "cfName"
	// Parser.g:1234:1: cfName[CFName name] : (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword | QMARK );
	public final void cfName(CFName name) throws RecognitionException {
		Token t=null;
		String k =null;

		try {
			// Parser.g:1235:5: (t= IDENT |t= QUOTED_NAME |k= unreserved_keyword | QMARK )
			int alt157=4;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt157=1;
				}
				break;
				case QUOTED_NAME:
				{
					alt157=2;
				}
				break;
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_ASCII:
				case K_BIGINT:
				case K_BLOB:
				case K_BOOLEAN:
				case K_CALLED:
				case K_CAST:
				case K_CLUSTERING:
				case K_COMPACT:
				case K_CONTAINS:
				case K_COUNT:
				case K_COUNTER:
				case K_CUSTOM:
				case K_DATE:
				case K_DECIMAL:
				case K_DISTINCT:
				case K_DOUBLE:
				case K_DURATION:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FLOAT:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INET:
				case K_INITCOND:
				case K_INPUT:
				case K_INT:
				case K_JSON:
				case K_KEY:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_SMALLINT:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TEXT:
				case K_TIME:
				case K_TIMESTAMP:
				case K_TIMEUUID:
				case K_TINYINT:
				case K_TRIGGER:
				case K_TTL:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_UUID:
				case K_VALUES:
				case K_VARCHAR:
				case K_VARINT:
				case K_WRITETIME:
				{
					alt157=3;
				}
				break;
				case QMARK:
				{
					alt157=4;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 157, 0, input);
					throw nvae;
			}
			switch (alt157) {
				case 1 :
					// Parser.g:1235:7: t= IDENT
				{
					t=(Token)match(input,IDENT,FOLLOW_IDENT_in_cfName8287);
					name.setColumnFamily((t!=null?t.getText():null), false);
				}
				break;
				case 2 :
					// Parser.g:1236:7: t= QUOTED_NAME
				{
					t=(Token)match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_cfName8312);
					name.setColumnFamily((t!=null?t.getText():null), true);
				}
				break;
				case 3 :
					// Parser.g:1237:7: k= unreserved_keyword
				{
					pushFollow(FOLLOW_unreserved_keyword_in_cfName8331);
					k=unreserved_keyword();
					state._fsp--;

					name.setColumnFamily(k, false);
				}
				break;
				case 4 :
					// Parser.g:1238:7: QMARK
				{
					match(input,QMARK,FOLLOW_QMARK_in_cfName8341);
					addRecognitionError("Bind variables cannot be used for table names");
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cfName"




	// $ANTLR start "constant"
	// Parser.g:1256:1: constant returns [Constants.Literal constant] : (t= STRING_LITERAL |t= INTEGER |t= FLOAT |t= BOOLEAN |t= DURATION |t= UUID |t= HEXNUMBER | ( '-' )? t= ( K_NAN | K_INFINITY ) );
	public final Constants.Literal constant() throws RecognitionException {
		Constants.Literal constant = null;


		Token t=null;

		try {
			// Parser.g:1257:5: (t= STRING_LITERAL |t= INTEGER |t= FLOAT |t= BOOLEAN |t= DURATION |t= UUID |t= HEXNUMBER | ( '-' )? t= ( K_NAN | K_INFINITY ) )
			int alt161=8;
			switch ( input.LA(1) ) {
				case STRING_LITERAL:
				{
					alt161=1;
				}
				break;
				case INTEGER:
				{
					alt161=2;
				}
				break;
				case FLOAT:
				{
					alt161=3;
				}
				break;
				case BOOLEAN:
				{
					alt161=4;
				}
				break;
				case DURATION:
				{
					alt161=5;
				}
				break;
				case UUID:
				{
					alt161=6;
				}
				break;
				case HEXNUMBER:
				{
					alt161=7;
				}
				break;
				case K_INFINITY:
				case K_NAN:
				case 188:
				{
					alt161=8;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 161, 0, input);
					throw nvae;
			}
			switch (alt161) {
				case 1 :
					// Parser.g:1257:7: t= STRING_LITERAL
				{
					t=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_constant8534);
					constant = Constants.Literal.string((t!=null?t.getText():null));
				}
				break;
				case 2 :
					// Parser.g:1258:7: t= INTEGER
				{
					t=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_constant8546);
					constant = Constants.Literal.integer((t!=null?t.getText():null));
				}
				break;
				case 3 :
					// Parser.g:1259:7: t= FLOAT
				{
					t=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_constant8565);
					constant = Constants.Literal.floatingPoint((t!=null?t.getText():null));
				}
				break;
				case 4 :
					// Parser.g:1260:7: t= BOOLEAN
				{
					t=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_constant8586);
					constant = Constants.Literal.bool((t!=null?t.getText():null));
				}
				break;
				case 5 :
					// Parser.g:1261:7: t= DURATION
				{
					t=(Token)match(input,DURATION,FOLLOW_DURATION_in_constant8605);
					constant = Constants.Literal.duration((t!=null?t.getText():null));
				}
				break;
				case 6 :
					// Parser.g:1262:7: t= UUID
				{
					t=(Token)match(input,UUID,FOLLOW_UUID_in_constant8623);
					constant = Constants.Literal.uuid((t!=null?t.getText():null));
				}
				break;
				case 7 :
					// Parser.g:1263:7: t= HEXNUMBER
				{
					t=(Token)match(input,HEXNUMBER,FOLLOW_HEXNUMBER_in_constant8645);
					constant = Constants.Literal.hex((t!=null?t.getText():null));
				}
				break;
				case 8 :
					// Parser.g:1264:7: ( '-' )? t= ( K_NAN | K_INFINITY )
				{
					String sign="";
					// Parser.g:1264:27: ( '-' )?
					int alt160=2;
					int LA160_0 = input.LA(1);
					if ( (LA160_0==188) ) {
						alt160=1;
					}
					switch (alt160) {
						case 1 :
							// Parser.g:1264:28: '-'
						{
							match(input,188,FOLLOW_188_in_constant8663);
							sign = "-";
						}
						break;

					}

					t=input.LT(1);
					if ( input.LA(1)==K_INFINITY||input.LA(1)==K_NAN ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					constant = Constants.Literal.floatingPoint(sign + (t!=null?t.getText():null));
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return constant;
	}
	// $ANTLR end "constant"



	// $ANTLR start "mapLiteral"
	// Parser.g:1267:1: mapLiteral returns [Maps.Literal map] : '{' (k1= term ':' v1= term ( ',' kn= term ':' vn= term )* )? '}' ;
	public final Maps.Literal mapLiteral() throws RecognitionException {
		Maps.Literal map = null;


		Term.Raw k1 =null;
		Term.Raw v1 =null;
		Term.Raw kn =null;
		Term.Raw vn =null;

		try {
			// Parser.g:1268:5: ( '{' (k1= term ':' v1= term ( ',' kn= term ':' vn= term )* )? '}' )
			// Parser.g:1268:7: '{' (k1= term ':' v1= term ( ',' kn= term ':' vn= term )* )? '}'
			{
				match(input,202,FOLLOW_202_in_mapLiteral8701);
				List<Pair<Term.Raw, Term.Raw>> m = new ArrayList<Pair<Term.Raw, Term.Raw>>();
				// Parser.g:1269:11: (k1= term ':' v1= term ( ',' kn= term ':' vn= term )* )?
				int alt163=2;
				int LA163_0 = input.LA(1);
				if ( (LA163_0==BOOLEAN||LA163_0==DURATION||LA163_0==FLOAT||LA163_0==HEXNUMBER||(LA163_0 >= IDENT && LA163_0 <= INTEGER)||(LA163_0 >= K_AGGREGATE && LA163_0 <= K_ALL)||LA163_0==K_AS||LA163_0==K_ASCII||(LA163_0 >= K_BIGINT && LA163_0 <= K_BOOLEAN)||(LA163_0 >= K_CALLED && LA163_0 <= K_CLUSTERING)||(LA163_0 >= K_COMPACT && LA163_0 <= K_COUNTER)||(LA163_0 >= K_CUSTOM && LA163_0 <= K_DECIMAL)||(LA163_0 >= K_DISTINCT && LA163_0 <= K_DOUBLE)||LA163_0==K_DURATION||(LA163_0 >= K_EXISTS && LA163_0 <= K_FLOAT)||LA163_0==K_FROZEN||(LA163_0 >= K_FUNCTION && LA163_0 <= K_FUNCTIONS)||LA163_0==K_GROUP||(LA163_0 >= K_INET && LA163_0 <= K_INPUT)||LA163_0==K_INT||(LA163_0 >= K_JSON && LA163_0 <= K_KEYS)||(LA163_0 >= K_KEYSPACES && LA163_0 <= K_LIKE)||(LA163_0 >= K_LIST && LA163_0 <= K_MAP)||(LA163_0 >= K_NAN && LA163_0 <= K_NOLOGIN)||LA163_0==K_NOSUPERUSER||LA163_0==K_NULL||LA163_0==K_OPTIONS||(LA163_0 >= K_PARTITION && LA163_0 <= K_PERMISSIONS)||LA163_0==K_RETURNS||(LA163_0 >= K_ROLE && LA163_0 <= K_ROLES)||(LA163_0 >= K_SFUNC && LA163_0 <= K_TINYINT)||(LA163_0 >= K_TOKEN && LA163_0 <= K_TRIGGER)||(LA163_0 >= K_TTL && LA163_0 <= K_TYPE)||(LA163_0 >= K_USER && LA163_0 <= K_USERS)||(LA163_0 >= K_UUID && LA163_0 <= K_VARINT)||LA163_0==K_WRITETIME||(LA163_0 >= QMARK && LA163_0 <= QUOTED_NAME)||LA163_0==STRING_LITERAL||LA163_0==UUID||LA163_0==183||LA163_0==188||LA163_0==191||LA163_0==198||LA163_0==202) ) {
					alt163=1;
				}
				switch (alt163) {
					case 1 :
						// Parser.g:1269:13: k1= term ':' v1= term ( ',' kn= term ':' vn= term )*
					{
						pushFollow(FOLLOW_term_in_mapLiteral8719);
						k1=term();
						state._fsp--;

						match(input,191,FOLLOW_191_in_mapLiteral8721);
						pushFollow(FOLLOW_term_in_mapLiteral8725);
						v1=term();
						state._fsp--;

						m.add(Pair.create(k1, v1));
						// Parser.g:1269:65: ( ',' kn= term ':' vn= term )*
						loop162:
						while (true) {
							int alt162=2;
							int LA162_0 = input.LA(1);
							if ( (LA162_0==187) ) {
								alt162=1;
							}

							switch (alt162) {
								case 1 :
									// Parser.g:1269:67: ',' kn= term ':' vn= term
								{
									match(input,187,FOLLOW_187_in_mapLiteral8731);
									pushFollow(FOLLOW_term_in_mapLiteral8735);
									kn=term();
									state._fsp--;

									match(input,191,FOLLOW_191_in_mapLiteral8737);
									pushFollow(FOLLOW_term_in_mapLiteral8741);
									vn=term();
									state._fsp--;

									m.add(Pair.create(kn, vn));
								}
								break;

								default :
									break loop162;
							}
						}

					}
					break;

				}

				match(input,203,FOLLOW_203_in_mapLiteral8757);
				map = new Maps.Literal(m);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return map;
	}
	// $ANTLR end "mapLiteral"



	// $ANTLR start "setOrMapLiteral"
	// Parser.g:1273:1: setOrMapLiteral[Term.Raw t] returns [Term.Raw value] : ( ':' v= term ( ',' kn= term ':' vn= term )* | ( ',' tn= term )* );
	public final Term.Raw setOrMapLiteral(Term.Raw t) throws RecognitionException {
		Term.Raw value = null;


		Term.Raw v =null;
		Term.Raw kn =null;
		Term.Raw vn =null;
		Term.Raw tn =null;

		try {
			// Parser.g:1274:5: ( ':' v= term ( ',' kn= term ':' vn= term )* | ( ',' tn= term )* )
			int alt166=2;
			int LA166_0 = input.LA(1);
			if ( (LA166_0==191) ) {
				alt166=1;
			}
			else if ( (LA166_0==187||LA166_0==203) ) {
				alt166=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 166, 0, input);
				throw nvae;
			}

			switch (alt166) {
				case 1 :
					// Parser.g:1274:7: ':' v= term ( ',' kn= term ':' vn= term )*
				{
					match(input,191,FOLLOW_191_in_setOrMapLiteral8781);
					pushFollow(FOLLOW_term_in_setOrMapLiteral8785);
					v=term();
					state._fsp--;

					List<Pair<Term.Raw, Term.Raw>> m = new ArrayList<Pair<Term.Raw, Term.Raw>>(); m.add(Pair.create(t, v));
					// Parser.g:1275:11: ( ',' kn= term ':' vn= term )*
					loop164:
					while (true) {
						int alt164=2;
						int LA164_0 = input.LA(1);
						if ( (LA164_0==187) ) {
							alt164=1;
						}

						switch (alt164) {
							case 1 :
								// Parser.g:1275:13: ',' kn= term ':' vn= term
							{
								match(input,187,FOLLOW_187_in_setOrMapLiteral8801);
								pushFollow(FOLLOW_term_in_setOrMapLiteral8805);
								kn=term();
								state._fsp--;

								match(input,191,FOLLOW_191_in_setOrMapLiteral8807);
								pushFollow(FOLLOW_term_in_setOrMapLiteral8811);
								vn=term();
								state._fsp--;

								m.add(Pair.create(kn, vn));
							}
							break;

							default :
								break loop164;
						}
					}

					value = new Maps.Literal(m);
				}
				break;
				case 2 :
					// Parser.g:1277:7: ( ',' tn= term )*
				{
					List<Term.Raw> s = new ArrayList<Term.Raw>(); s.add(t);
					// Parser.g:1278:11: ( ',' tn= term )*
					loop165:
					while (true) {
						int alt165=2;
						int LA165_0 = input.LA(1);
						if ( (LA165_0==187) ) {
							alt165=1;
						}

						switch (alt165) {
							case 1 :
								// Parser.g:1278:13: ',' tn= term
							{
								match(input,187,FOLLOW_187_in_setOrMapLiteral8846);
								pushFollow(FOLLOW_term_in_setOrMapLiteral8850);
								tn=term();
								state._fsp--;

								s.add(tn);
							}
							break;

							default :
								break loop165;
						}
					}

					value = new Sets.Literal(s);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "setOrMapLiteral"



	// $ANTLR start "collectionLiteral"
	// Parser.g:1282:1: collectionLiteral returns [Term.Raw value] : ( '[' (t1= term ( ',' tn= term )* )? ']' | '{' t= term v= setOrMapLiteral[t] '}' | '{' '}' );
	public final Term.Raw collectionLiteral() throws RecognitionException {
		Term.Raw value = null;


		Term.Raw t1 =null;
		Term.Raw tn =null;
		Term.Raw t =null;
		Term.Raw v =null;

		try {
			// Parser.g:1283:5: ( '[' (t1= term ( ',' tn= term )* )? ']' | '{' t= term v= setOrMapLiteral[t] '}' | '{' '}' )
			int alt169=3;
			int LA169_0 = input.LA(1);
			if ( (LA169_0==198) ) {
				alt169=1;
			}
			else if ( (LA169_0==202) ) {
				int LA169_2 = input.LA(2);
				if ( (LA169_2==203) ) {
					alt169=3;
				}
				else if ( (LA169_2==BOOLEAN||LA169_2==DURATION||LA169_2==FLOAT||LA169_2==HEXNUMBER||(LA169_2 >= IDENT && LA169_2 <= INTEGER)||(LA169_2 >= K_AGGREGATE && LA169_2 <= K_ALL)||LA169_2==K_AS||LA169_2==K_ASCII||(LA169_2 >= K_BIGINT && LA169_2 <= K_BOOLEAN)||(LA169_2 >= K_CALLED && LA169_2 <= K_CLUSTERING)||(LA169_2 >= K_COMPACT && LA169_2 <= K_COUNTER)||(LA169_2 >= K_CUSTOM && LA169_2 <= K_DECIMAL)||(LA169_2 >= K_DISTINCT && LA169_2 <= K_DOUBLE)||LA169_2==K_DURATION||(LA169_2 >= K_EXISTS && LA169_2 <= K_FLOAT)||LA169_2==K_FROZEN||(LA169_2 >= K_FUNCTION && LA169_2 <= K_FUNCTIONS)||LA169_2==K_GROUP||(LA169_2 >= K_INET && LA169_2 <= K_INPUT)||LA169_2==K_INT||(LA169_2 >= K_JSON && LA169_2 <= K_KEYS)||(LA169_2 >= K_KEYSPACES && LA169_2 <= K_LIKE)||(LA169_2 >= K_LIST && LA169_2 <= K_MAP)||(LA169_2 >= K_NAN && LA169_2 <= K_NOLOGIN)||LA169_2==K_NOSUPERUSER||LA169_2==K_NULL||LA169_2==K_OPTIONS||(LA169_2 >= K_PARTITION && LA169_2 <= K_PERMISSIONS)||LA169_2==K_RETURNS||(LA169_2 >= K_ROLE && LA169_2 <= K_ROLES)||(LA169_2 >= K_SFUNC && LA169_2 <= K_TINYINT)||(LA169_2 >= K_TOKEN && LA169_2 <= K_TRIGGER)||(LA169_2 >= K_TTL && LA169_2 <= K_TYPE)||(LA169_2 >= K_USER && LA169_2 <= K_USERS)||(LA169_2 >= K_UUID && LA169_2 <= K_VARINT)||LA169_2==K_WRITETIME||(LA169_2 >= QMARK && LA169_2 <= QUOTED_NAME)||LA169_2==STRING_LITERAL||LA169_2==UUID||LA169_2==183||LA169_2==188||LA169_2==191||LA169_2==198||LA169_2==202) ) {
					alt169=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
								new NoViableAltException("", 169, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 169, 0, input);
				throw nvae;
			}

			switch (alt169) {
				case 1 :
					// Parser.g:1283:7: '[' (t1= term ( ',' tn= term )* )? ']'
				{
					match(input,198,FOLLOW_198_in_collectionLiteral8884);
					List<Term.Raw> l = new ArrayList<Term.Raw>();
					// Parser.g:1284:11: (t1= term ( ',' tn= term )* )?
					int alt168=2;
					int LA168_0 = input.LA(1);
					if ( (LA168_0==BOOLEAN||LA168_0==DURATION||LA168_0==FLOAT||LA168_0==HEXNUMBER||(LA168_0 >= IDENT && LA168_0 <= INTEGER)||(LA168_0 >= K_AGGREGATE && LA168_0 <= K_ALL)||LA168_0==K_AS||LA168_0==K_ASCII||(LA168_0 >= K_BIGINT && LA168_0 <= K_BOOLEAN)||(LA168_0 >= K_CALLED && LA168_0 <= K_CLUSTERING)||(LA168_0 >= K_COMPACT && LA168_0 <= K_COUNTER)||(LA168_0 >= K_CUSTOM && LA168_0 <= K_DECIMAL)||(LA168_0 >= K_DISTINCT && LA168_0 <= K_DOUBLE)||LA168_0==K_DURATION||(LA168_0 >= K_EXISTS && LA168_0 <= K_FLOAT)||LA168_0==K_FROZEN||(LA168_0 >= K_FUNCTION && LA168_0 <= K_FUNCTIONS)||LA168_0==K_GROUP||(LA168_0 >= K_INET && LA168_0 <= K_INPUT)||LA168_0==K_INT||(LA168_0 >= K_JSON && LA168_0 <= K_KEYS)||(LA168_0 >= K_KEYSPACES && LA168_0 <= K_LIKE)||(LA168_0 >= K_LIST && LA168_0 <= K_MAP)||(LA168_0 >= K_NAN && LA168_0 <= K_NOLOGIN)||LA168_0==K_NOSUPERUSER||LA168_0==K_NULL||LA168_0==K_OPTIONS||(LA168_0 >= K_PARTITION && LA168_0 <= K_PERMISSIONS)||LA168_0==K_RETURNS||(LA168_0 >= K_ROLE && LA168_0 <= K_ROLES)||(LA168_0 >= K_SFUNC && LA168_0 <= K_TINYINT)||(LA168_0 >= K_TOKEN && LA168_0 <= K_TRIGGER)||(LA168_0 >= K_TTL && LA168_0 <= K_TYPE)||(LA168_0 >= K_USER && LA168_0 <= K_USERS)||(LA168_0 >= K_UUID && LA168_0 <= K_VARINT)||LA168_0==K_WRITETIME||(LA168_0 >= QMARK && LA168_0 <= QUOTED_NAME)||LA168_0==STRING_LITERAL||LA168_0==UUID||LA168_0==183||LA168_0==188||LA168_0==191||LA168_0==198||LA168_0==202) ) {
						alt168=1;
					}
					switch (alt168) {
						case 1 :
							// Parser.g:1284:13: t1= term ( ',' tn= term )*
						{
							pushFollow(FOLLOW_term_in_collectionLiteral8902);
							t1=term();
							state._fsp--;

							l.add(t1);
							// Parser.g:1284:36: ( ',' tn= term )*
							loop167:
							while (true) {
								int alt167=2;
								int LA167_0 = input.LA(1);
								if ( (LA167_0==187) ) {
									alt167=1;
								}

								switch (alt167) {
									case 1 :
										// Parser.g:1284:38: ',' tn= term
									{
										match(input,187,FOLLOW_187_in_collectionLiteral8908);
										pushFollow(FOLLOW_term_in_collectionLiteral8912);
										tn=term();
										state._fsp--;

										l.add(tn);
									}
									break;

									default :
										break loop167;
								}
							}

						}
						break;

					}

					match(input,200,FOLLOW_200_in_collectionLiteral8928);
					value = new Lists.Literal(l);
				}
				break;
				case 2 :
					// Parser.g:1286:7: '{' t= term v= setOrMapLiteral[t] '}'
				{
					match(input,202,FOLLOW_202_in_collectionLiteral8938);
					pushFollow(FOLLOW_term_in_collectionLiteral8942);
					t=term();
					state._fsp--;

					pushFollow(FOLLOW_setOrMapLiteral_in_collectionLiteral8946);
					v=setOrMapLiteral(t);
					state._fsp--;

					value = v;
					match(input,203,FOLLOW_203_in_collectionLiteral8951);
				}
				break;
				case 3 :
					// Parser.g:1289:7: '{' '}'
				{
					match(input,202,FOLLOW_202_in_collectionLiteral8969);
					match(input,203,FOLLOW_203_in_collectionLiteral8971);
					value = new Sets.Literal(Collections.<Term.Raw>emptyList());
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "collectionLiteral"



	// $ANTLR start "usertypeLiteral"
	// Parser.g:1292:1: usertypeLiteral returns [UserTypes.Literal ut] : '{' k1= fident ':' v1= term ( ',' kn= fident ':' vn= term )* '}' ;
	public final UserTypes.Literal usertypeLiteral() throws RecognitionException {
		UserTypes.Literal ut = null;


		FieldIdentifier k1 =null;
		Term.Raw v1 =null;
		FieldIdentifier kn =null;
		Term.Raw vn =null;

		Map<FieldIdentifier, Term.Raw> m = new HashMap<>();
		try {
			// Parser.g:1296:5: ( '{' k1= fident ':' v1= term ( ',' kn= fident ':' vn= term )* '}' )
			// Parser.g:1296:7: '{' k1= fident ':' v1= term ( ',' kn= fident ':' vn= term )* '}'
			{
				match(input,202,FOLLOW_202_in_usertypeLiteral9015);
				pushFollow(FOLLOW_fident_in_usertypeLiteral9019);
				k1=fident();
				state._fsp--;

				match(input,191,FOLLOW_191_in_usertypeLiteral9021);
				pushFollow(FOLLOW_term_in_usertypeLiteral9025);
				v1=term();
				state._fsp--;

				m.put(k1, v1);
				// Parser.g:1296:52: ( ',' kn= fident ':' vn= term )*
				loop170:
				while (true) {
					int alt170=2;
					int LA170_0 = input.LA(1);
					if ( (LA170_0==187) ) {
						alt170=1;
					}

					switch (alt170) {
						case 1 :
							// Parser.g:1296:54: ',' kn= fident ':' vn= term
						{
							match(input,187,FOLLOW_187_in_usertypeLiteral9031);
							pushFollow(FOLLOW_fident_in_usertypeLiteral9035);
							kn=fident();
							state._fsp--;

							match(input,191,FOLLOW_191_in_usertypeLiteral9037);
							pushFollow(FOLLOW_term_in_usertypeLiteral9041);
							vn=term();
							state._fsp--;

							m.put(kn, vn);
						}
						break;

						default :
							break loop170;
					}
				}

				match(input,203,FOLLOW_203_in_usertypeLiteral9048);
			}

			ut = new UserTypes.Literal(m);
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ut;
	}
	// $ANTLR end "usertypeLiteral"



	// $ANTLR start "tupleLiteral"
	// Parser.g:1299:1: tupleLiteral returns [Tuples.Literal tt] : '(' t1= term ( ',' tn= term )* ')' ;
	public final Tuples.Literal tupleLiteral() throws RecognitionException {
		Tuples.Literal tt = null;


		Term.Raw t1 =null;
		Term.Raw tn =null;

		List<Term.Raw> l = new ArrayList<Term.Raw>();
		try {
			// Parser.g:1302:5: ( '(' t1= term ( ',' tn= term )* ')' )
			// Parser.g:1302:7: '(' t1= term ( ',' tn= term )* ')'
			{
				match(input,183,FOLLOW_183_in_tupleLiteral9085);
				pushFollow(FOLLOW_term_in_tupleLiteral9089);
				t1=term();
				state._fsp--;

				l.add(t1);
				// Parser.g:1302:34: ( ',' tn= term )*
				loop171:
				while (true) {
					int alt171=2;
					int LA171_0 = input.LA(1);
					if ( (LA171_0==187) ) {
						alt171=1;
					}

					switch (alt171) {
						case 1 :
							// Parser.g:1302:36: ',' tn= term
						{
							match(input,187,FOLLOW_187_in_tupleLiteral9095);
							pushFollow(FOLLOW_term_in_tupleLiteral9099);
							tn=term();
							state._fsp--;

							l.add(tn);
						}
						break;

						default :
							break loop171;
					}
				}

				match(input,184,FOLLOW_184_in_tupleLiteral9106);
			}

			tt = new Tuples.Literal(l);
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return tt;
	}
	// $ANTLR end "tupleLiteral"



	// $ANTLR start "value"
	// Parser.g:1305:1: value returns [Term.Raw value] : (c= constant |l= collectionLiteral |u= usertypeLiteral |t= tupleLiteral | K_NULL | ':' id= noncol_ident | QMARK );
	public final Term.Raw value() throws RecognitionException {
		Term.Raw value = null;


		Constants.Literal c =null;
		Term.Raw l =null;
		UserTypes.Literal u =null;
		Tuples.Literal t =null;
		ColumnIdentifier id =null;

		try {
			// Parser.g:1306:5: (c= constant |l= collectionLiteral |u= usertypeLiteral |t= tupleLiteral | K_NULL | ':' id= noncol_ident | QMARK )
			int alt172=7;
			alt172 = dfa172.predict(input);
			switch (alt172) {
				case 1 :
					// Parser.g:1306:7: c= constant
				{
					pushFollow(FOLLOW_constant_in_value9129);
					c=constant();
					state._fsp--;

					value = c;
				}
				break;
				case 2 :
					// Parser.g:1307:7: l= collectionLiteral
				{
					pushFollow(FOLLOW_collectionLiteral_in_value9151);
					l=collectionLiteral();
					state._fsp--;

					value = l;
				}
				break;
				case 3 :
					// Parser.g:1308:7: u= usertypeLiteral
				{
					pushFollow(FOLLOW_usertypeLiteral_in_value9164);
					u=usertypeLiteral();
					state._fsp--;

					value = u;
				}
				break;
				case 4 :
					// Parser.g:1309:7: t= tupleLiteral
				{
					pushFollow(FOLLOW_tupleLiteral_in_value9179);
					t=tupleLiteral();
					state._fsp--;

					value = t;
				}
				break;
				case 5 :
					// Parser.g:1310:7: K_NULL
				{
					match(input,K_NULL,FOLLOW_K_NULL_in_value9195);
					value = Constants.NULL_LITERAL;
				}
				break;
				case 6 :
					// Parser.g:1311:7: ':' id= noncol_ident
				{
					match(input,191,FOLLOW_191_in_value9219);
					pushFollow(FOLLOW_noncol_ident_in_value9223);
					id=noncol_ident();
					state._fsp--;

					value = newBindVariables(id);
				}
				break;
				case 7 :
					// Parser.g:1312:7: QMARK
				{
					match(input,QMARK,FOLLOW_QMARK_in_value9234);
					value = newBindVariables(null);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "value"



	// $ANTLR start "intValue"
	// Parser.g:1315:1: intValue returns [Term.Raw value] : (t= INTEGER | ':' id= noncol_ident | QMARK );
	public final Term.Raw intValue() throws RecognitionException {
		Term.Raw value = null;


		Token t=null;
		ColumnIdentifier id =null;

		try {
			// Parser.g:1316:5: (t= INTEGER | ':' id= noncol_ident | QMARK )
			int alt173=3;
			switch ( input.LA(1) ) {
				case INTEGER:
				{
					alt173=1;
				}
				break;
				case 191:
				{
					alt173=2;
				}
				break;
				case QMARK:
				{
					alt173=3;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 173, 0, input);
					throw nvae;
			}
			switch (alt173) {
				case 1 :
					// Parser.g:1316:7: t= INTEGER
				{
					t=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_intValue9274);
					value = Constants.Literal.integer((t!=null?t.getText():null));
				}
				break;
				case 2 :
					// Parser.g:1317:7: ':' id= noncol_ident
				{
					match(input,191,FOLLOW_191_in_intValue9288);
					pushFollow(FOLLOW_noncol_ident_in_intValue9292);
					id=noncol_ident();
					state._fsp--;

					value = newBindVariables(id);
				}
				break;
				case 3 :
					// Parser.g:1318:7: QMARK
				{
					match(input,QMARK,FOLLOW_QMARK_in_intValue9303);
					value = newBindVariables(null);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "intValue"


	// $ANTLR start "term"
	// Parser.g:1343:1: term returns [Term.Raw term] : (v= value |f= function | '(' c= comparatorType ')' t= term );
	public final Term.Raw term() throws RecognitionException {
		Term.Raw term = null;


		Term.Raw v =null;
		Term.Raw f =null;
		CQL3Type.Raw c =null;
		Term.Raw t =null;

		try {
			// Parser.g:1344:5: (v= value |f= function | '(' c= comparatorType ')' t= term )
			int alt178=3;
			alt178 = dfa178.predict(input);
			switch (alt178) {
				case 1 :
					// Parser.g:1344:7: v= value
				{
					pushFollow(FOLLOW_value_in_term9636);
					v=value();
					state._fsp--;

					term = v;
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return term;
	}
	// $ANTLR end "term"


	// $ANTLR start "properties"
	// Parser.g:1436:1: properties[PropertyDefinitions props] : property[props] ( K_AND property[props] )* ;
	public final void properties(PropertyDefinitions props) throws RecognitionException {
		try {
			// Parser.g:1437:5: ( property[props] ( K_AND property[props] )* )
			// Parser.g:1437:7: property[props] ( K_AND property[props] )*
			{
				pushFollow(FOLLOW_property_in_properties10406);
				property(props);
				state._fsp--;

				// Parser.g:1437:23: ( K_AND property[props] )*
				loop188:
				while (true) {
					int alt188=2;
					int LA188_0 = input.LA(1);
					if ( (LA188_0==K_AND) ) {
						alt188=1;
					}

					switch (alt188) {
						case 1 :
							// Parser.g:1437:24: K_AND property[props]
						{
							match(input,K_AND,FOLLOW_K_AND_in_properties10410);
							pushFollow(FOLLOW_property_in_properties10412);
							property(props);
							state._fsp--;

						}
						break;

						default :
							break loop188;
					}
				}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "properties"



	// $ANTLR start "property"
	// Parser.g:1440:1: property[PropertyDefinitions props] : (k= noncol_ident '=' simple= propertyValue |k= noncol_ident '=' map= mapLiteral );
	public final void property(PropertyDefinitions props) throws RecognitionException {
		ColumnIdentifier k =null;
		String simple =null;
		Maps.Literal map =null;

		try {
			// Parser.g:1441:5: (k= noncol_ident '=' simple= propertyValue |k= noncol_ident '=' map= mapLiteral )
			int alt189=2;
			alt189 = dfa189.predict(input);
			switch (alt189) {
				case 1 :
					// Parser.g:1441:7: k= noncol_ident '=' simple= propertyValue
				{
					pushFollow(FOLLOW_noncol_ident_in_property10435);
					k=noncol_ident();
					state._fsp--;

					match(input,195,FOLLOW_195_in_property10437);
					pushFollow(FOLLOW_propertyValue_in_property10441);
					simple=propertyValue();
					state._fsp--;

					try { props.addProperty(k.toString(), simple); } catch (SyntaxException e) { addRecognitionError(e.getMessage()); }
				}
				break;
				case 2 :
					// Parser.g:1442:7: k= noncol_ident '=' map= mapLiteral
				{
					pushFollow(FOLLOW_noncol_ident_in_property10453);
					k=noncol_ident();
					state._fsp--;

					match(input,195,FOLLOW_195_in_property10455);
					pushFollow(FOLLOW_mapLiteral_in_property10459);
					map=mapLiteral();
					state._fsp--;

					try { props.addProperty(k.toString(), convertPropertyMap(map)); } catch (SyntaxException e) { addRecognitionError(e.getMessage()); }
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "property"



	// $ANTLR start "propertyValue"
	// Parser.g:1445:1: propertyValue returns [String str] : (c= constant |u= unreserved_keyword );
	public final String propertyValue() throws RecognitionException {
		String str = null;


		Constants.Literal c =null;
		String u =null;

		try {
			// Parser.g:1446:5: (c= constant |u= unreserved_keyword )
			int alt190=2;
			int LA190_0 = input.LA(1);
			if ( (LA190_0==BOOLEAN||LA190_0==DURATION||LA190_0==FLOAT||LA190_0==HEXNUMBER||LA190_0==INTEGER||LA190_0==K_INFINITY||LA190_0==K_NAN||LA190_0==STRING_LITERAL||LA190_0==UUID||LA190_0==188) ) {
				alt190=1;
			}
			else if ( ((LA190_0 >= K_AGGREGATE && LA190_0 <= K_ALL)||LA190_0==K_AS||LA190_0==K_ASCII||(LA190_0 >= K_BIGINT && LA190_0 <= K_BOOLEAN)||(LA190_0 >= K_CALLED && LA190_0 <= K_CLUSTERING)||(LA190_0 >= K_COMPACT && LA190_0 <= K_COUNTER)||(LA190_0 >= K_CUSTOM && LA190_0 <= K_DECIMAL)||(LA190_0 >= K_DISTINCT && LA190_0 <= K_DOUBLE)||LA190_0==K_DURATION||(LA190_0 >= K_EXISTS && LA190_0 <= K_FLOAT)||LA190_0==K_FROZEN||(LA190_0 >= K_FUNCTION && LA190_0 <= K_FUNCTIONS)||LA190_0==K_GROUP||LA190_0==K_INET||(LA190_0 >= K_INITCOND && LA190_0 <= K_INPUT)||LA190_0==K_INT||(LA190_0 >= K_JSON && LA190_0 <= K_KEYS)||(LA190_0 >= K_KEYSPACES && LA190_0 <= K_LIKE)||(LA190_0 >= K_LIST && LA190_0 <= K_MAP)||LA190_0==K_NOLOGIN||LA190_0==K_NOSUPERUSER||LA190_0==K_OPTIONS||(LA190_0 >= K_PARTITION && LA190_0 <= K_PERMISSIONS)||LA190_0==K_RETURNS||(LA190_0 >= K_ROLE && LA190_0 <= K_ROLES)||(LA190_0 >= K_SFUNC && LA190_0 <= K_TINYINT)||LA190_0==K_TRIGGER||(LA190_0 >= K_TTL && LA190_0 <= K_TYPE)||(LA190_0 >= K_USER && LA190_0 <= K_USERS)||(LA190_0 >= K_UUID && LA190_0 <= K_VARINT)||LA190_0==K_WRITETIME) ) {
				alt190=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 190, 0, input);
				throw nvae;
			}

			switch (alt190) {
				case 1 :
					// Parser.g:1446:7: c= constant
				{
					pushFollow(FOLLOW_constant_in_propertyValue10484);
					c=constant();
					state._fsp--;

					str = c.getRawText();
				}
				break;
				case 2 :
					// Parser.g:1447:7: u= unreserved_keyword
				{
					pushFollow(FOLLOW_unreserved_keyword_in_propertyValue10506);
					u=unreserved_keyword();
					state._fsp--;

					str = u;
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return str;
	}
	// $ANTLR end "propertyValue"



	// $ANTLR start "inMarker"
	// Parser.g:1495:1: inMarker returns [AbstractMarker.INRaw marker] : ( QMARK | ':' name= noncol_ident );
	public final AbstractMarker.INRaw inMarker() throws RecognitionException {
		AbstractMarker.INRaw marker = null;


		ColumnIdentifier name =null;

		try {
			// Parser.g:1496:5: ( QMARK | ':' name= noncol_ident )
			int alt196=2;
			int LA196_0 = input.LA(1);
			if ( (LA196_0==QMARK) ) {
				alt196=1;
			}
			else if ( (LA196_0==191) ) {
				alt196=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 196, 0, input);
				throw nvae;
			}

			switch (alt196) {
				case 1 :
					// Parser.g:1496:7: QMARK
				{
					match(input,QMARK,FOLLOW_QMARK_in_inMarker11063);
					marker = newINBindVariables(null);
				}
				break;
				case 2 :
					// Parser.g:1497:7: ':' name= noncol_ident
				{
					match(input,191,FOLLOW_191_in_inMarker11073);
					pushFollow(FOLLOW_noncol_ident_in_inMarker11077);
					name=noncol_ident();
					state._fsp--;

					marker = newINBindVariables(name);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return marker;
	}
	// $ANTLR end "inMarker"



	// $ANTLR start "tupleOfIdentifiers"
	// Parser.g:1500:1: tupleOfIdentifiers returns [List<ColumnDefinition.Raw> ids] : '(' n1= cident ( ',' ni= cident )* ')' ;
	public final List<ColumnDefinition.Raw> tupleOfIdentifiers() throws RecognitionException {
		List<ColumnDefinition.Raw> ids = null;


		ColumnDefinition.Raw n1 =null;
		ColumnDefinition.Raw ni =null;

		ids = new ArrayList<ColumnDefinition.Raw>();
		try {
			// Parser.g:1502:5: ( '(' n1= cident ( ',' ni= cident )* ')' )
			// Parser.g:1502:7: '(' n1= cident ( ',' ni= cident )* ')'
			{
				match(input,183,FOLLOW_183_in_tupleOfIdentifiers11109);
				pushFollow(FOLLOW_cident_in_tupleOfIdentifiers11113);
				n1=cident();
				state._fsp--;

				ids.add(n1);
				// Parser.g:1502:39: ( ',' ni= cident )*
				loop197:
				while (true) {
					int alt197=2;
					int LA197_0 = input.LA(1);
					if ( (LA197_0==187) ) {
						alt197=1;
					}

					switch (alt197) {
						case 1 :
							// Parser.g:1502:40: ',' ni= cident
						{
							match(input,187,FOLLOW_187_in_tupleOfIdentifiers11118);
							pushFollow(FOLLOW_cident_in_tupleOfIdentifiers11122);
							ni=cident();
							state._fsp--;

							ids.add(ni);
						}
						break;

						default :
							break loop197;
					}
				}

				match(input,184,FOLLOW_184_in_tupleOfIdentifiers11128);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ids;
	}
	// $ANTLR end "tupleOfIdentifiers"



	// $ANTLR start "singleColumnInValues"
	// Parser.g:1505:1: singleColumnInValues returns [List<Term.Raw> terms] : '(' (t1= term ( ',' ti= term )* )? ')' ;
	public final List<Term.Raw> singleColumnInValues() throws RecognitionException {
		List<Term.Raw> terms = null;


		Term.Raw t1 =null;
		Term.Raw ti =null;

		terms = new ArrayList<Term.Raw>();
		try {
			// Parser.g:1507:5: ( '(' (t1= term ( ',' ti= term )* )? ')' )
			// Parser.g:1507:7: '(' (t1= term ( ',' ti= term )* )? ')'
			{
				match(input,183,FOLLOW_183_in_singleColumnInValues11158);
				// Parser.g:1507:11: (t1= term ( ',' ti= term )* )?
				int alt199=2;
				int LA199_0 = input.LA(1);
				if ( (LA199_0==BOOLEAN||LA199_0==DURATION||LA199_0==FLOAT||LA199_0==HEXNUMBER||(LA199_0 >= IDENT && LA199_0 <= INTEGER)||(LA199_0 >= K_AGGREGATE && LA199_0 <= K_ALL)||LA199_0==K_AS||LA199_0==K_ASCII||(LA199_0 >= K_BIGINT && LA199_0 <= K_BOOLEAN)||(LA199_0 >= K_CALLED && LA199_0 <= K_CLUSTERING)||(LA199_0 >= K_COMPACT && LA199_0 <= K_COUNTER)||(LA199_0 >= K_CUSTOM && LA199_0 <= K_DECIMAL)||(LA199_0 >= K_DISTINCT && LA199_0 <= K_DOUBLE)||LA199_0==K_DURATION||(LA199_0 >= K_EXISTS && LA199_0 <= K_FLOAT)||LA199_0==K_FROZEN||(LA199_0 >= K_FUNCTION && LA199_0 <= K_FUNCTIONS)||LA199_0==K_GROUP||(LA199_0 >= K_INET && LA199_0 <= K_INPUT)||LA199_0==K_INT||(LA199_0 >= K_JSON && LA199_0 <= K_KEYS)||(LA199_0 >= K_KEYSPACES && LA199_0 <= K_LIKE)||(LA199_0 >= K_LIST && LA199_0 <= K_MAP)||(LA199_0 >= K_NAN && LA199_0 <= K_NOLOGIN)||LA199_0==K_NOSUPERUSER||LA199_0==K_NULL||LA199_0==K_OPTIONS||(LA199_0 >= K_PARTITION && LA199_0 <= K_PERMISSIONS)||LA199_0==K_RETURNS||(LA199_0 >= K_ROLE && LA199_0 <= K_ROLES)||(LA199_0 >= K_SFUNC && LA199_0 <= K_TINYINT)||(LA199_0 >= K_TOKEN && LA199_0 <= K_TRIGGER)||(LA199_0 >= K_TTL && LA199_0 <= K_TYPE)||(LA199_0 >= K_USER && LA199_0 <= K_USERS)||(LA199_0 >= K_UUID && LA199_0 <= K_VARINT)||LA199_0==K_WRITETIME||(LA199_0 >= QMARK && LA199_0 <= QUOTED_NAME)||LA199_0==STRING_LITERAL||LA199_0==UUID||LA199_0==183||LA199_0==188||LA199_0==191||LA199_0==198||LA199_0==202) ) {
					alt199=1;
				}
				switch (alt199) {
					case 1 :
						// Parser.g:1507:13: t1= term ( ',' ti= term )*
					{
						pushFollow(FOLLOW_term_in_singleColumnInValues11166);
						t1=term();
						state._fsp--;

						terms.add(t1);
						// Parser.g:1507:43: ( ',' ti= term )*
						loop198:
						while (true) {
							int alt198=2;
							int LA198_0 = input.LA(1);
							if ( (LA198_0==187) ) {
								alt198=1;
							}

							switch (alt198) {
								case 1 :
									// Parser.g:1507:44: ',' ti= term
								{
									match(input,187,FOLLOW_187_in_singleColumnInValues11171);
									pushFollow(FOLLOW_term_in_singleColumnInValues11175);
									ti=term();
									state._fsp--;

									terms.add(ti);
								}
								break;

								default :
									break loop198;
							}
						}

					}
					break;

				}

				match(input,184,FOLLOW_184_in_singleColumnInValues11184);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return terms;
	}
	// $ANTLR end "singleColumnInValues"



	// $ANTLR start "tupleOfTupleLiterals"
	// Parser.g:1510:1: tupleOfTupleLiterals returns [List<Tuples.Literal> literals] : '(' t1= tupleLiteral ( ',' ti= tupleLiteral )* ')' ;
	public final List<Tuples.Literal> tupleOfTupleLiterals() throws RecognitionException {
		List<Tuples.Literal> literals = null;


		Tuples.Literal t1 =null;
		Tuples.Literal ti =null;

		literals = new ArrayList<>();
		try {
			// Parser.g:1512:5: ( '(' t1= tupleLiteral ( ',' ti= tupleLiteral )* ')' )
			// Parser.g:1512:7: '(' t1= tupleLiteral ( ',' ti= tupleLiteral )* ')'
			{
				match(input,183,FOLLOW_183_in_tupleOfTupleLiterals11214);
				pushFollow(FOLLOW_tupleLiteral_in_tupleOfTupleLiterals11218);
				t1=tupleLiteral();
				state._fsp--;

				literals.add(t1);
				// Parser.g:1512:50: ( ',' ti= tupleLiteral )*
				loop200:
				while (true) {
					int alt200=2;
					int LA200_0 = input.LA(1);
					if ( (LA200_0==187) ) {
						alt200=1;
					}

					switch (alt200) {
						case 1 :
							// Parser.g:1512:51: ',' ti= tupleLiteral
						{
							match(input,187,FOLLOW_187_in_tupleOfTupleLiterals11223);
							pushFollow(FOLLOW_tupleLiteral_in_tupleOfTupleLiterals11227);
							ti=tupleLiteral();
							state._fsp--;

							literals.add(ti);
						}
						break;

						default :
							break loop200;
					}
				}

				match(input,184,FOLLOW_184_in_tupleOfTupleLiterals11233);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return literals;
	}
	// $ANTLR end "tupleOfTupleLiterals"



	// $ANTLR start "markerForTuple"
	// Parser.g:1515:1: markerForTuple returns [Tuples.Raw marker] : ( QMARK | ':' name= noncol_ident );
	public final Tuples.Raw markerForTuple() throws RecognitionException {
		Tuples.Raw marker = null;


		ColumnIdentifier name =null;

		try {
			// Parser.g:1516:5: ( QMARK | ':' name= noncol_ident )
			int alt201=2;
			int LA201_0 = input.LA(1);
			if ( (LA201_0==QMARK) ) {
				alt201=1;
			}
			else if ( (LA201_0==191) ) {
				alt201=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 201, 0, input);
				throw nvae;
			}

			switch (alt201) {
				case 1 :
					// Parser.g:1516:7: QMARK
				{
					match(input,QMARK,FOLLOW_QMARK_in_markerForTuple11254);
					marker = newTupleBindVariables(null);
				}
				break;
				case 2 :
					// Parser.g:1517:7: ':' name= noncol_ident
				{
					match(input,191,FOLLOW_191_in_markerForTuple11264);
					pushFollow(FOLLOW_noncol_ident_in_markerForTuple11268);
					name=noncol_ident();
					state._fsp--;

					marker = newTupleBindVariables(name);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return marker;
	}
	// $ANTLR end "markerForTuple"



	// $ANTLR start "tupleOfMarkersForTuples"
	// Parser.g:1520:1: tupleOfMarkersForTuples returns [List<Tuples.Raw> markers] : '(' m1= markerForTuple ( ',' mi= markerForTuple )* ')' ;
	public final List<Tuples.Raw> tupleOfMarkersForTuples() throws RecognitionException {
		List<Tuples.Raw> markers = null;


		Tuples.Raw m1 =null;
		Tuples.Raw mi =null;

		markers = new ArrayList<Tuples.Raw>();
		try {
			// Parser.g:1522:5: ( '(' m1= markerForTuple ( ',' mi= markerForTuple )* ')' )
			// Parser.g:1522:7: '(' m1= markerForTuple ( ',' mi= markerForTuple )* ')'
			{
				match(input,183,FOLLOW_183_in_tupleOfMarkersForTuples11300);
				pushFollow(FOLLOW_markerForTuple_in_tupleOfMarkersForTuples11304);
				m1=markerForTuple();
				state._fsp--;

				markers.add(m1);
				// Parser.g:1522:51: ( ',' mi= markerForTuple )*
				loop202:
				while (true) {
					int alt202=2;
					int LA202_0 = input.LA(1);
					if ( (LA202_0==187) ) {
						alt202=1;
					}

					switch (alt202) {
						case 1 :
							// Parser.g:1522:52: ',' mi= markerForTuple
						{
							match(input,187,FOLLOW_187_in_tupleOfMarkersForTuples11309);
							pushFollow(FOLLOW_markerForTuple_in_tupleOfMarkersForTuples11313);
							mi=markerForTuple();
							state._fsp--;

							markers.add(mi);
						}
						break;

						default :
							break loop202;
					}
				}

				match(input,184,FOLLOW_184_in_tupleOfMarkersForTuples11319);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return markers;
	}
	// $ANTLR end "tupleOfMarkersForTuples"



	// $ANTLR start "inMarkerForTuple"
	// Parser.g:1525:1: inMarkerForTuple returns [Tuples.INRaw marker] : ( QMARK | ':' name= noncol_ident );
	public final Tuples.INRaw inMarkerForTuple() throws RecognitionException {
		Tuples.INRaw marker = null;


		ColumnIdentifier name =null;

		try {
			// Parser.g:1526:5: ( QMARK | ':' name= noncol_ident )
			int alt203=2;
			int LA203_0 = input.LA(1);
			if ( (LA203_0==QMARK) ) {
				alt203=1;
			}
			else if ( (LA203_0==191) ) {
				alt203=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 203, 0, input);
				throw nvae;
			}

			switch (alt203) {
				case 1 :
					// Parser.g:1526:7: QMARK
				{
					match(input,QMARK,FOLLOW_QMARK_in_inMarkerForTuple11340);
					marker = newTupleINBindVariables(null);
				}
				break;
				case 2 :
					// Parser.g:1527:7: ':' name= noncol_ident
				{
					match(input,191,FOLLOW_191_in_inMarkerForTuple11350);
					pushFollow(FOLLOW_noncol_ident_in_inMarkerForTuple11354);
					name=noncol_ident();
					state._fsp--;

					marker = newTupleINBindVariables(name);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return marker;
	}
	// $ANTLR end "inMarkerForTuple"



	// $ANTLR start "comparatorType"
	// Parser.g:1530:1: comparatorType returns [CQL3Type.Raw t] : (n= native_type |c= collection_type |tt= tuple_type |id= userTypeName | K_FROZEN '<' f= comparatorType '>' |s= STRING_LITERAL );
	public final CQL3Type.Raw comparatorType() throws RecognitionException {
		CQL3Type.Raw t = null;


		Token s=null;
		CQL3Type n =null;
		CQL3Type.Raw c =null;
		CQL3Type.Raw tt =null;
		UTName id =null;
		CQL3Type.Raw f =null;

		try {
			// Parser.g:1531:5: (n= native_type |c= collection_type |tt= tuple_type |id= userTypeName | K_FROZEN '<' f= comparatorType '>' |s= STRING_LITERAL )
			int alt204=6;
			alt204 = dfa204.predict(input);
			switch (alt204) {
				case 1 :
					// Parser.g:1531:7: n= native_type
				{
					pushFollow(FOLLOW_native_type_in_comparatorType11379);
					n=native_type();
					state._fsp--;

					t = CQL3Type.Raw.from(n);
				}
				break;
				case 2 :
					// Parser.g:1532:7: c= collection_type
				{
					pushFollow(FOLLOW_collection_type_in_comparatorType11395);
					c=collection_type();
					state._fsp--;

					t = c;
				}
				break;
				case 3 :
					// Parser.g:1533:7: tt= tuple_type
				{
					pushFollow(FOLLOW_tuple_type_in_comparatorType11407);
					tt=tuple_type();
					state._fsp--;

					t = tt;
				}
				break;
				case 5 :
					// Parser.g:1535:7: K_FROZEN '<' f= comparatorType '>'
				{
					match(input,K_FROZEN,FOLLOW_K_FROZEN_in_comparatorType11435);
					match(input,193,FOLLOW_193_in_comparatorType11437);
					pushFollow(FOLLOW_comparatorType_in_comparatorType11441);
					f=comparatorType();
					state._fsp--;

					match(input,196,FOLLOW_196_in_comparatorType11443);

					try {
						t = CQL3Type.Raw.frozen(f);
					} catch (InvalidRequestException e) {
						addRecognitionError(e.getMessage());
					}

				}
				break;
				case 6 :
					// Parser.g:1543:7: s= STRING_LITERAL
				{
					s=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_comparatorType11461);

					try {
						t = CQL3Type.Raw.from(new CQL3Type.Custom((s!=null?s.getText():null)));
					} catch (SyntaxException e) {
						addRecognitionError("Cannot parse type " + (s!=null?s.getText():null) + ": " + e.getMessage());
					} catch (ConfigurationException e) {
						addRecognitionError("Error setting type " + (s!=null?s.getText():null) + ": " + e.getMessage());
					}

				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "comparatorType"



	// $ANTLR start "native_type"
	// Parser.g:1555:1: native_type returns [CQL3Type t] : ( K_ASCII | K_BIGINT | K_BLOB | K_BOOLEAN | K_COUNTER | K_DECIMAL | K_DOUBLE | K_DURATION | K_FLOAT | K_INET | K_INT | K_SMALLINT | K_TEXT | K_TIMESTAMP | K_TINYINT | K_UUID | K_VARCHAR | K_VARINT | K_TIMEUUID | K_DATE | K_TIME );
	public final CQL3Type native_type() throws RecognitionException {
		CQL3Type t = null;


		try {
			// Parser.g:1556:5: ( K_ASCII | K_BIGINT | K_BLOB | K_BOOLEAN | K_COUNTER | K_DECIMAL | K_DOUBLE | K_DURATION | K_FLOAT | K_INET | K_INT | K_SMALLINT | K_TEXT | K_TIMESTAMP | K_TINYINT | K_UUID | K_VARCHAR | K_VARINT | K_TIMEUUID | K_DATE | K_TIME )
			int alt205=21;
			switch ( input.LA(1) ) {
				case K_ASCII:
				{
					alt205=1;
				}
				break;
				case K_BIGINT:
				{
					alt205=2;
				}
				break;
				case K_BLOB:
				{
					alt205=3;
				}
				break;
				case K_BOOLEAN:
				{
					alt205=4;
				}
				break;
				case K_COUNTER:
				{
					alt205=5;
				}
				break;
				case K_DECIMAL:
				{
					alt205=6;
				}
				break;
				case K_DOUBLE:
				{
					alt205=7;
				}
				break;
				case K_DURATION:
				{
					alt205=8;
				}
				break;
				case K_FLOAT:
				{
					alt205=9;
				}
				break;
				case K_INET:
				{
					alt205=10;
				}
				break;
				case K_INT:
				{
					alt205=11;
				}
				break;
				case K_SMALLINT:
				{
					alt205=12;
				}
				break;
				case K_TEXT:
				{
					alt205=13;
				}
				break;
				case K_TIMESTAMP:
				{
					alt205=14;
				}
				break;
				case K_TINYINT:
				{
					alt205=15;
				}
				break;
				case K_UUID:
				{
					alt205=16;
				}
				break;
				case K_VARCHAR:
				{
					alt205=17;
				}
				break;
				case K_VARINT:
				{
					alt205=18;
				}
				break;
				case K_TIMEUUID:
				{
					alt205=19;
				}
				break;
				case K_DATE:
				{
					alt205=20;
				}
				break;
				case K_TIME:
				{
					alt205=21;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 205, 0, input);
					throw nvae;
			}
			switch (alt205) {
				case 1 :
					// Parser.g:1556:7: K_ASCII
				{
					match(input,K_ASCII,FOLLOW_K_ASCII_in_native_type11490);
					t = CQL3Type.Native.ASCII;
				}
				break;
				case 2 :
					// Parser.g:1557:7: K_BIGINT
				{
					match(input,K_BIGINT,FOLLOW_K_BIGINT_in_native_type11504);
					t = CQL3Type.Native.BIGINT;
				}
				break;
				case 3 :
					// Parser.g:1558:7: K_BLOB
				{
					match(input,K_BLOB,FOLLOW_K_BLOB_in_native_type11517);
					t = CQL3Type.Native.BLOB;
				}
				break;
				case 4 :
					// Parser.g:1559:7: K_BOOLEAN
				{
					match(input,K_BOOLEAN,FOLLOW_K_BOOLEAN_in_native_type11532);
					t = CQL3Type.Native.BOOLEAN;
				}
				break;
				case 5 :
					// Parser.g:1560:7: K_COUNTER
				{
					match(input,K_COUNTER,FOLLOW_K_COUNTER_in_native_type11544);
					t = CQL3Type.Native.COUNTER;
				}
				break;
				case 6 :
					// Parser.g:1561:7: K_DECIMAL
				{
					match(input,K_DECIMAL,FOLLOW_K_DECIMAL_in_native_type11556);
					t = CQL3Type.Native.DECIMAL;
				}
				break;
				case 7 :
					// Parser.g:1562:7: K_DOUBLE
				{
					match(input,K_DOUBLE,FOLLOW_K_DOUBLE_in_native_type11568);
					t = CQL3Type.Native.DOUBLE;
				}
				break;
				case 8 :
					// Parser.g:1563:7: K_DURATION
				{
					match(input,K_DURATION,FOLLOW_K_DURATION_in_native_type11581);
					t = CQL3Type.Native.DURATION;
				}
				break;
				case 9 :
					// Parser.g:1564:7: K_FLOAT
				{
					match(input,K_FLOAT,FOLLOW_K_FLOAT_in_native_type11594);
					t = CQL3Type.Native.FLOAT;
				}
				break;
				case 10 :
					// Parser.g:1565:7: K_INET
				{
					match(input,K_INET,FOLLOW_K_INET_in_native_type11608);
					t = CQL3Type.Native.INET;
				}
				break;
				case 11 :
					// Parser.g:1566:7: K_INT
				{
					match(input,K_INT,FOLLOW_K_INT_in_native_type11623);
					t = CQL3Type.Native.INT;
				}
				break;
				case 12 :
					// Parser.g:1567:7: K_SMALLINT
				{
					match(input,K_SMALLINT,FOLLOW_K_SMALLINT_in_native_type11639);
					t = CQL3Type.Native.SMALLINT;
				}
				break;
				case 13 :
					// Parser.g:1568:7: K_TEXT
				{
					match(input,K_TEXT,FOLLOW_K_TEXT_in_native_type11650);
					t = CQL3Type.Native.TEXT;
				}
				break;
				case 14 :
					// Parser.g:1569:7: K_TIMESTAMP
				{
					match(input,K_TIMESTAMP,FOLLOW_K_TIMESTAMP_in_native_type11665);
					t = CQL3Type.Native.TIMESTAMP;
				}
				break;
				case 15 :
					// Parser.g:1570:7: K_TINYINT
				{
					match(input,K_TINYINT,FOLLOW_K_TINYINT_in_native_type11675);
					t = CQL3Type.Native.TINYINT;
				}
				break;
				case 16 :
					// Parser.g:1571:7: K_UUID
				{
					match(input,K_UUID,FOLLOW_K_UUID_in_native_type11687);
					t = CQL3Type.Native.UUID;
				}
				break;
				case 17 :
					// Parser.g:1572:7: K_VARCHAR
				{
					match(input,K_VARCHAR,FOLLOW_K_VARCHAR_in_native_type11702);
					t = CQL3Type.Native.VARCHAR;
				}
				break;
				case 18 :
					// Parser.g:1573:7: K_VARINT
				{
					match(input,K_VARINT,FOLLOW_K_VARINT_in_native_type11714);
					t = CQL3Type.Native.VARINT;
				}
				break;
				case 19 :
					// Parser.g:1574:7: K_TIMEUUID
				{
					match(input,K_TIMEUUID,FOLLOW_K_TIMEUUID_in_native_type11727);
					t = CQL3Type.Native.TIMEUUID;
				}
				break;
				case 20 :
					// Parser.g:1575:7: K_DATE
				{
					match(input,K_DATE,FOLLOW_K_DATE_in_native_type11738);
					t = CQL3Type.Native.DATE;
				}
				break;
				case 21 :
					// Parser.g:1576:7: K_TIME
				{
					match(input,K_TIME,FOLLOW_K_TIME_in_native_type11753);
					t = CQL3Type.Native.TIME;
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "native_type"



	// $ANTLR start "collection_type"
	// Parser.g:1579:1: collection_type returns [CQL3Type.Raw pt] : ( K_MAP '<' t1= comparatorType ',' t2= comparatorType '>' | K_LIST '<' t= comparatorType '>' | K_SET '<' t= comparatorType '>' );
	public final CQL3Type.Raw collection_type() throws RecognitionException {
		CQL3Type.Raw pt = null;


		CQL3Type.Raw t1 =null;
		CQL3Type.Raw t2 =null;
		CQL3Type.Raw t =null;

		try {
			// Parser.g:1580:5: ( K_MAP '<' t1= comparatorType ',' t2= comparatorType '>' | K_LIST '<' t= comparatorType '>' | K_SET '<' t= comparatorType '>' )
			int alt206=3;
			switch ( input.LA(1) ) {
				case K_MAP:
				{
					alt206=1;
				}
				break;
				case K_LIST:
				{
					alt206=2;
				}
				break;
				case K_SET:
				{
					alt206=3;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 206, 0, input);
					throw nvae;
			}
			switch (alt206) {
				case 1 :
					// Parser.g:1580:7: K_MAP '<' t1= comparatorType ',' t2= comparatorType '>'
				{
					match(input,K_MAP,FOLLOW_K_MAP_in_collection_type11781);
					match(input,193,FOLLOW_193_in_collection_type11784);
					pushFollow(FOLLOW_comparatorType_in_collection_type11788);
					t1=comparatorType();
					state._fsp--;

					match(input,187,FOLLOW_187_in_collection_type11790);
					pushFollow(FOLLOW_comparatorType_in_collection_type11794);
					t2=comparatorType();
					state._fsp--;

					match(input,196,FOLLOW_196_in_collection_type11796);

					// if we can't parse either t1 or t2, antlr will "recover" and we may have t1 or t2 null.
					if (t1 != null && t2 != null)
						pt = CQL3Type.Raw.map(t1, t2);

				}
				break;
				case 2 :
					// Parser.g:1586:7: K_LIST '<' t= comparatorType '>'
				{
					match(input,K_LIST,FOLLOW_K_LIST_in_collection_type11814);
					match(input,193,FOLLOW_193_in_collection_type11816);
					pushFollow(FOLLOW_comparatorType_in_collection_type11820);
					t=comparatorType();
					state._fsp--;

					match(input,196,FOLLOW_196_in_collection_type11822);
					if (t != null) pt = CQL3Type.Raw.list(t);
				}
				break;
				case 3 :
					// Parser.g:1588:7: K_SET '<' t= comparatorType '>'
				{
					match(input,K_SET,FOLLOW_K_SET_in_collection_type11840);
					match(input,193,FOLLOW_193_in_collection_type11843);
					pushFollow(FOLLOW_comparatorType_in_collection_type11847);
					t=comparatorType();
					state._fsp--;

					match(input,196,FOLLOW_196_in_collection_type11849);
					if (t != null) pt = CQL3Type.Raw.set(t);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return pt;
	}
	// $ANTLR end "collection_type"



	// $ANTLR start "tuple_type"
	// Parser.g:1592:1: tuple_type returns [CQL3Type.Raw t] : K_TUPLE '<' t1= comparatorType ( ',' tn= comparatorType )* '>' ;
	public final CQL3Type.Raw tuple_type() throws RecognitionException {
		CQL3Type.Raw t = null;


		CQL3Type.Raw t1 =null;
		CQL3Type.Raw tn =null;

		try {
			// Parser.g:1593:5: ( K_TUPLE '<' t1= comparatorType ( ',' tn= comparatorType )* '>' )
			// Parser.g:1593:7: K_TUPLE '<' t1= comparatorType ( ',' tn= comparatorType )* '>'
			{
				match(input,K_TUPLE,FOLLOW_K_TUPLE_in_tuple_type11880);
				match(input,193,FOLLOW_193_in_tuple_type11882);
				List<CQL3Type.Raw> types = new ArrayList<>();
				pushFollow(FOLLOW_comparatorType_in_tuple_type11897);
				t1=comparatorType();
				state._fsp--;

				types.add(t1);
				// Parser.g:1594:47: ( ',' tn= comparatorType )*
				loop207:
				while (true) {
					int alt207=2;
					int LA207_0 = input.LA(1);
					if ( (LA207_0==187) ) {
						alt207=1;
					}

					switch (alt207) {
						case 1 :
							// Parser.g:1594:48: ',' tn= comparatorType
						{
							match(input,187,FOLLOW_187_in_tuple_type11902);
							pushFollow(FOLLOW_comparatorType_in_tuple_type11906);
							tn=comparatorType();
							state._fsp--;

							types.add(tn);
						}
						break;

						default :
							break loop207;
					}
				}

				match(input,196,FOLLOW_196_in_tuple_type11918);
				t = CQL3Type.Raw.tuple(types);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "tuple_type"


	public static class username_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "username"
	// Parser.g:1598:1: username : ( IDENT | STRING_LITERAL | QUOTED_NAME );
	public final Cql_Parser.username_return username() throws RecognitionException {
		Cql_Parser.username_return retval = new Cql_Parser.username_return();
		retval.start = input.LT(1);

		try {
			// Parser.g:1599:5: ( IDENT | STRING_LITERAL | QUOTED_NAME )
			int alt208=3;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt208=1;
				}
				break;
				case STRING_LITERAL:
				{
					alt208=2;
				}
				break;
				case QUOTED_NAME:
				{
					alt208=3;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 208, 0, input);
					throw nvae;
			}
			switch (alt208) {
				case 1 :
					// Parser.g:1599:7: IDENT
				{
					match(input,IDENT,FOLLOW_IDENT_in_username11937);
				}
				break;
				case 2 :
					// Parser.g:1600:7: STRING_LITERAL
				{
					match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_username11945);
				}
				break;
				case 3 :
					// Parser.g:1601:7: QUOTED_NAME
				{
					match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_username11953);
					addRecognitionError("Quoted strings are are not supported for user names and USER is deprecated, please use ROLE");
				}
				break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "username"


	public static class mbean_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "mbean"
	// Parser.g:1604:1: mbean : STRING_LITERAL ;
	public final Cql_Parser.mbean_return mbean() throws RecognitionException {
		Cql_Parser.mbean_return retval = new Cql_Parser.mbean_return();
		retval.start = input.LT(1);

		try {
			// Parser.g:1605:5: ( STRING_LITERAL )
			// Parser.g:1605:7: STRING_LITERAL
			{
				match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_mbean11972);
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "mbean"



	// $ANTLR start "non_type_ident"
	// Parser.g:1610:1: non_type_ident returns [ColumnIdentifier id] : (t= IDENT |t= QUOTED_NAME |k= basic_unreserved_keyword |kk= K_KEY );
	public final ColumnIdentifier non_type_ident() throws RecognitionException {
		ColumnIdentifier id = null;


		Token t=null;
		Token kk=null;
		String k =null;

		try {
			// Parser.g:1611:5: (t= IDENT |t= QUOTED_NAME |k= basic_unreserved_keyword |kk= K_KEY )
			int alt209=4;
			switch ( input.LA(1) ) {
				case IDENT:
				{
					alt209=1;
				}
				break;
				case QUOTED_NAME:
				{
					alt209=2;
				}
				break;
				case K_AGGREGATE:
				case K_ALL:
				case K_AS:
				case K_CALLED:
				case K_CLUSTERING:
				case K_COMPACT:
				case K_CONTAINS:
				case K_CUSTOM:
				case K_EXISTS:
				case K_FILTERING:
				case K_FINALFUNC:
				case K_FROZEN:
				case K_FUNCTION:
				case K_FUNCTIONS:
				case K_GROUP:
				case K_INITCOND:
				case K_INPUT:
				case K_KEYS:
				case K_KEYSPACES:
				case K_LANGUAGE:
				case K_LIKE:
				case K_LIST:
				case K_LOGIN:
				case K_MAP:
				case K_NOLOGIN:
				case K_NOSUPERUSER:
				case K_OPTIONS:
				case K_PARTITION:
				case K_PASSWORD:
				case K_PER:
				case K_PERMISSION:
				case K_PERMISSIONS:
				case K_RETURNS:
				case K_ROLE:
				case K_ROLES:
				case K_SFUNC:
				case K_STATIC:
				case K_STORAGE:
				case K_STYPE:
				case K_SUPERUSER:
				case K_TRIGGER:
				case K_TUPLE:
				case K_TYPE:
				case K_USER:
				case K_USERS:
				case K_VALUES:
				{
					alt209=3;
				}
				break;
				case K_KEY:
				{
					alt209=4;
				}
				break;
				default:
					NoViableAltException nvae =
							new NoViableAltException("", 209, 0, input);
					throw nvae;
			}
			switch (alt209) {
				case 1 :
					// Parser.g:1611:7: t= IDENT
				{
					t=(Token)match(input,IDENT,FOLLOW_IDENT_in_non_type_ident11997);
					if (reservedTypeNames.contains((t!=null?t.getText():null))) addRecognitionError("Invalid (reserved) user type name " + (t!=null?t.getText():null)); id = new ColumnIdentifier((t!=null?t.getText():null), false);
				}
				break;
				case 2 :
					// Parser.g:1612:7: t= QUOTED_NAME
				{
					t=(Token)match(input,QUOTED_NAME,FOLLOW_QUOTED_NAME_in_non_type_ident12028);
					id = new ColumnIdentifier((t!=null?t.getText():null), true);
				}
				break;
				case 3 :
					// Parser.g:1613:7: k= basic_unreserved_keyword
				{
					pushFollow(FOLLOW_basic_unreserved_keyword_in_non_type_ident12053);
					k=basic_unreserved_keyword();
					state._fsp--;

					id = new ColumnIdentifier(k, false);
				}
				break;
				case 4 :
					// Parser.g:1614:7: kk= K_KEY
				{
					kk=(Token)match(input,K_KEY,FOLLOW_K_KEY_in_non_type_ident12065);
					id = new ColumnIdentifier((kk!=null?kk.getText():null), false);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return id;
	}
	// $ANTLR end "non_type_ident"



	// $ANTLR start "unreserved_keyword"
	// Parser.g:1617:1: unreserved_keyword returns [String str] : (u= unreserved_function_keyword |k= ( K_TTL | K_COUNT | K_WRITETIME | K_KEY | K_CAST | K_JSON | K_DISTINCT ) );
	public final String unreserved_keyword() throws RecognitionException {
		String str = null;


		Token k=null;
		String u =null;

		try {
			// Parser.g:1618:5: (u= unreserved_function_keyword |k= ( K_TTL | K_COUNT | K_WRITETIME | K_KEY | K_CAST | K_JSON | K_DISTINCT ) )
			int alt210=2;
			int LA210_0 = input.LA(1);
			if ( ((LA210_0 >= K_AGGREGATE && LA210_0 <= K_ALL)||LA210_0==K_AS||LA210_0==K_ASCII||(LA210_0 >= K_BIGINT && LA210_0 <= K_BOOLEAN)||LA210_0==K_CALLED||LA210_0==K_CLUSTERING||(LA210_0 >= K_COMPACT && LA210_0 <= K_CONTAINS)||LA210_0==K_COUNTER||(LA210_0 >= K_CUSTOM && LA210_0 <= K_DECIMAL)||LA210_0==K_DOUBLE||LA210_0==K_DURATION||(LA210_0 >= K_EXISTS && LA210_0 <= K_FLOAT)||LA210_0==K_FROZEN||(LA210_0 >= K_FUNCTION && LA210_0 <= K_FUNCTIONS)||LA210_0==K_GROUP||LA210_0==K_INET||(LA210_0 >= K_INITCOND && LA210_0 <= K_INPUT)||LA210_0==K_INT||LA210_0==K_KEYS||(LA210_0 >= K_KEYSPACES && LA210_0 <= K_LIKE)||(LA210_0 >= K_LIST && LA210_0 <= K_MAP)||LA210_0==K_NOLOGIN||LA210_0==K_NOSUPERUSER||LA210_0==K_OPTIONS||(LA210_0 >= K_PARTITION && LA210_0 <= K_PERMISSIONS)||LA210_0==K_RETURNS||(LA210_0 >= K_ROLE && LA210_0 <= K_ROLES)||(LA210_0 >= K_SFUNC && LA210_0 <= K_TINYINT)||LA210_0==K_TRIGGER||(LA210_0 >= K_TUPLE && LA210_0 <= K_TYPE)||(LA210_0 >= K_USER && LA210_0 <= K_USERS)||(LA210_0 >= K_UUID && LA210_0 <= K_VARINT)) ) {
				alt210=1;
			}
			else if ( (LA210_0==K_CAST||LA210_0==K_COUNT||LA210_0==K_DISTINCT||(LA210_0 >= K_JSON && LA210_0 <= K_KEY)||LA210_0==K_TTL||LA210_0==K_WRITETIME) ) {
				alt210=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 210, 0, input);
				throw nvae;
			}

			switch (alt210) {
				case 1 :
					// Parser.g:1618:7: u= unreserved_function_keyword
				{
					pushFollow(FOLLOW_unreserved_function_keyword_in_unreserved_keyword12108);
					u=unreserved_function_keyword();
					state._fsp--;

					str = u;
				}
				break;
				case 2 :
					// Parser.g:1619:7: k= ( K_TTL | K_COUNT | K_WRITETIME | K_KEY | K_CAST | K_JSON | K_DISTINCT )
				{
					k=input.LT(1);
					if ( input.LA(1)==K_CAST||input.LA(1)==K_COUNT||input.LA(1)==K_DISTINCT||(input.LA(1) >= K_JSON && input.LA(1) <= K_KEY)||input.LA(1)==K_TTL||input.LA(1)==K_WRITETIME ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					str = (k!=null?k.getText():null);
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return str;
	}
	// $ANTLR end "unreserved_keyword"



	// $ANTLR start "unreserved_function_keyword"
	// Parser.g:1622:1: unreserved_function_keyword returns [String str] : (u= basic_unreserved_keyword |t= native_type );
	public final String unreserved_function_keyword() throws RecognitionException {
		String str = null;


		String u =null;
		CQL3Type t =null;

		try {
			// Parser.g:1623:5: (u= basic_unreserved_keyword |t= native_type )
			int alt211=2;
			int LA211_0 = input.LA(1);
			if ( ((LA211_0 >= K_AGGREGATE && LA211_0 <= K_ALL)||LA211_0==K_AS||LA211_0==K_CALLED||LA211_0==K_CLUSTERING||(LA211_0 >= K_COMPACT && LA211_0 <= K_CONTAINS)||LA211_0==K_CUSTOM||(LA211_0 >= K_EXISTS && LA211_0 <= K_FINALFUNC)||LA211_0==K_FROZEN||(LA211_0 >= K_FUNCTION && LA211_0 <= K_FUNCTIONS)||LA211_0==K_GROUP||(LA211_0 >= K_INITCOND && LA211_0 <= K_INPUT)||LA211_0==K_KEYS||(LA211_0 >= K_KEYSPACES && LA211_0 <= K_LIKE)||(LA211_0 >= K_LIST && LA211_0 <= K_MAP)||LA211_0==K_NOLOGIN||LA211_0==K_NOSUPERUSER||LA211_0==K_OPTIONS||(LA211_0 >= K_PARTITION && LA211_0 <= K_PERMISSIONS)||LA211_0==K_RETURNS||(LA211_0 >= K_ROLE && LA211_0 <= K_ROLES)||LA211_0==K_SFUNC||(LA211_0 >= K_STATIC && LA211_0 <= K_SUPERUSER)||LA211_0==K_TRIGGER||(LA211_0 >= K_TUPLE && LA211_0 <= K_TYPE)||(LA211_0 >= K_USER && LA211_0 <= K_USERS)||LA211_0==K_VALUES) ) {
				alt211=1;
			}
			else if ( (LA211_0==K_ASCII||(LA211_0 >= K_BIGINT && LA211_0 <= K_BOOLEAN)||LA211_0==K_COUNTER||(LA211_0 >= K_DATE && LA211_0 <= K_DECIMAL)||LA211_0==K_DOUBLE||LA211_0==K_DURATION||LA211_0==K_FLOAT||LA211_0==K_INET||LA211_0==K_INT||LA211_0==K_SMALLINT||(LA211_0 >= K_TEXT && LA211_0 <= K_TINYINT)||LA211_0==K_UUID||(LA211_0 >= K_VARCHAR && LA211_0 <= K_VARINT)) ) {
				alt211=2;
			}

			else {
				NoViableAltException nvae =
						new NoViableAltException("", 211, 0, input);
				throw nvae;
			}

			switch (alt211) {
				case 1 :
					// Parser.g:1623:7: u= basic_unreserved_keyword
				{
					pushFollow(FOLLOW_basic_unreserved_keyword_in_unreserved_function_keyword12175);
					u=basic_unreserved_keyword();
					state._fsp--;

					str = u;
				}
				break;
				case 2 :
					// Parser.g:1624:7: t= native_type
				{
					pushFollow(FOLLOW_native_type_in_unreserved_function_keyword12187);
					t=native_type();
					state._fsp--;

					str = t.toString();
				}
				break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return str;
	}
	// $ANTLR end "unreserved_function_keyword"



	// $ANTLR start "basic_unreserved_keyword"
	// Parser.g:1627:1: basic_unreserved_keyword returns [String str] : k= ( K_KEYS | K_AS | K_CLUSTERING | K_COMPACT | K_STORAGE | K_TYPE | K_VALUES | K_MAP | K_LIST | K_FILTERING | K_PERMISSION | K_PERMISSIONS | K_KEYSPACES | K_ALL | K_USER | K_USERS | K_ROLE | K_ROLES | K_SUPERUSER | K_NOSUPERUSER | K_LOGIN | K_NOLOGIN | K_OPTIONS | K_PASSWORD | K_EXISTS | K_CUSTOM | K_TRIGGER | K_CONTAINS | K_STATIC | K_FROZEN | K_TUPLE | K_FUNCTION | K_FUNCTIONS | K_AGGREGATE | K_SFUNC | K_STYPE | K_FINALFUNC | K_INITCOND | K_RETURNS | K_LANGUAGE | K_CALLED | K_INPUT | K_LIKE | K_PER | K_PARTITION | K_GROUP ) ;
	public final String basic_unreserved_keyword() throws RecognitionException {
		String str = null;


		Token k=null;

		try {
			// Parser.g:1628:5: (k= ( K_KEYS | K_AS | K_CLUSTERING | K_COMPACT | K_STORAGE | K_TYPE | K_VALUES | K_MAP | K_LIST | K_FILTERING | K_PERMISSION | K_PERMISSIONS | K_KEYSPACES | K_ALL | K_USER | K_USERS | K_ROLE | K_ROLES | K_SUPERUSER | K_NOSUPERUSER | K_LOGIN | K_NOLOGIN | K_OPTIONS | K_PASSWORD | K_EXISTS | K_CUSTOM | K_TRIGGER | K_CONTAINS | K_STATIC | K_FROZEN | K_TUPLE | K_FUNCTION | K_FUNCTIONS | K_AGGREGATE | K_SFUNC | K_STYPE | K_FINALFUNC | K_INITCOND | K_RETURNS | K_LANGUAGE | K_CALLED | K_INPUT | K_LIKE | K_PER | K_PARTITION | K_GROUP ) )
			// Parser.g:1628:7: k= ( K_KEYS | K_AS | K_CLUSTERING | K_COMPACT | K_STORAGE | K_TYPE | K_VALUES | K_MAP | K_LIST | K_FILTERING | K_PERMISSION | K_PERMISSIONS | K_KEYSPACES | K_ALL | K_USER | K_USERS | K_ROLE | K_ROLES | K_SUPERUSER | K_NOSUPERUSER | K_LOGIN | K_NOLOGIN | K_OPTIONS | K_PASSWORD | K_EXISTS | K_CUSTOM | K_TRIGGER | K_CONTAINS | K_STATIC | K_FROZEN | K_TUPLE | K_FUNCTION | K_FUNCTIONS | K_AGGREGATE | K_SFUNC | K_STYPE | K_FINALFUNC | K_INITCOND | K_RETURNS | K_LANGUAGE | K_CALLED | K_INPUT | K_LIKE | K_PER | K_PARTITION | K_GROUP )
			{
				k=input.LT(1);
				if ( (input.LA(1) >= K_AGGREGATE && input.LA(1) <= K_ALL)||input.LA(1)==K_AS||input.LA(1)==K_CALLED||input.LA(1)==K_CLUSTERING||(input.LA(1) >= K_COMPACT && input.LA(1) <= K_CONTAINS)||input.LA(1)==K_CUSTOM||(input.LA(1) >= K_EXISTS && input.LA(1) <= K_FINALFUNC)||input.LA(1)==K_FROZEN||(input.LA(1) >= K_FUNCTION && input.LA(1) <= K_FUNCTIONS)||input.LA(1)==K_GROUP||(input.LA(1) >= K_INITCOND && input.LA(1) <= K_INPUT)||input.LA(1)==K_KEYS||(input.LA(1) >= K_KEYSPACES && input.LA(1) <= K_LIKE)||(input.LA(1) >= K_LIST && input.LA(1) <= K_MAP)||input.LA(1)==K_NOLOGIN||input.LA(1)==K_NOSUPERUSER||input.LA(1)==K_OPTIONS||(input.LA(1) >= K_PARTITION && input.LA(1) <= K_PERMISSIONS)||input.LA(1)==K_RETURNS||(input.LA(1) >= K_ROLE && input.LA(1) <= K_ROLES)||input.LA(1)==K_SFUNC||(input.LA(1) >= K_STATIC && input.LA(1) <= K_SUPERUSER)||input.LA(1)==K_TRIGGER||(input.LA(1) >= K_TUPLE && input.LA(1) <= K_TYPE)||(input.LA(1) >= K_USER && input.LA(1) <= K_USERS)||input.LA(1)==K_VALUES ) {
					input.consume();
					state.errorRecovery=false;
				}
				else {
					MismatchedSetException mse = new MismatchedSetException(null,input);
					throw mse;
				}
				str = (k!=null?k.getText():null);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return str;
	}
	// $ANTLR end "basic_unreserved_keyword"

	// Delegated rules


	protected DFA1 dfa1 = new DFA1(this);
	protected DFA15 dfa15 = new DFA15(this);
	protected DFA44 dfa44 = new DFA44(this);
	protected DFA153 dfa153 = new DFA153(this);
	protected DFA154 dfa154 = new DFA154(this);
	protected DFA172 dfa172 = new DFA172(this);
	protected DFA174 dfa174 = new DFA174(this);
	protected DFA176 dfa176 = new DFA176(this);
	protected DFA178 dfa178 = new DFA178(this);
	protected DFA181 dfa181 = new DFA181(this);
	protected DFA189 dfa189 = new DFA189(this);
	protected DFA195 dfa195 = new DFA195(this);
	protected DFA194 dfa194 = new DFA194(this);
	protected DFA204 dfa204 = new DFA204(this);
	static final String DFA1_eotS =
			"\63\uffff";
	static final String DFA1_eofS =
			"\63\uffff";
	static final String DFA1_minS =
			"\1\36\7\uffff\2\33\1\56\2\26\1\34\10\uffff\1\170\22\uffff\1\155\2\uffff"+
					"\1\105\5\uffff\1\33";
	static final String DFA1_maxS =
			"\1\u0094\7\uffff\3\u0095\2\u00ac\1\u0096\10\uffff\1\170\22\uffff\1\u008a"+
					"\2\uffff\1\165\5\uffff\1\110";
	static final String DFA1_acceptS =
			"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\6\uffff\1\10\1\11\1\23\1\27\1\31"+
					"\1\40\1\46\1\12\1\uffff\1\34\1\36\1\13\1\14\1\15\1\25\1\30\1\33\1\35\1"+
					"\37\1\42\1\47\1\16\1\17\1\24\1\32\1\41\1\50\1\uffff\1\20\1\44\1\uffff"+
					"\1\21\1\45\1\26\1\43\1\22\1\uffff";
	static final String DFA1_specialS =
			"\63\uffff}>";
	static final String[] DFA1_transitionS = {
			"\1\12\7\uffff\1\4\14\uffff\1\10\4\uffff\1\5\4\uffff\1\11\14\uffff\1\13"+
					"\10\uffff\1\2\13\uffff\1\15\32\uffff\1\14\2\uffff\1\1\17\uffff\1\7\5"+
					"\uffff\1\3\1\6",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\30\22\uffff\1\17\5\uffff\1\25\23\uffff\1\27\5\uffff\1\25\13\uffff"+
					"\1\16\7\uffff\1\24\14\uffff\1\26\13\uffff\1\23\20\uffff\1\21\3\uffff"+
					"\1\22\4\uffff\1\20",
			"\1\40\22\uffff\1\32\31\uffff\1\37\5\uffff\1\33\13\uffff\1\31\7\uffff"+
					"\1\42\30\uffff\1\41\20\uffff\1\35\3\uffff\1\36\4\uffff\1\34",
			"\1\43\53\uffff\1\44\7\uffff\1\50\30\uffff\1\47\24\uffff\1\46\4\uffff"+
					"\1\45",
			"\1\53\4\uffff\1\53\1\51\1\uffff\1\52\2\uffff\1\53\1\uffff\1\53\1\52"+
					"\2\uffff\3\53\1\uffff\3\53\1\uffff\4\53\1\52\3\53\3\uffff\1\52\2\53\1"+
					"\52\1\53\1\uffff\1\52\4\53\1\uffff\1\53\1\uffff\2\53\1\uffff\1\53\3\uffff"+
					"\1\53\1\uffff\2\53\1\uffff\1\53\2\uffff\3\53\1\uffff\3\53\1\uffff\3\53"+
					"\3\uffff\1\52\1\uffff\1\53\1\uffff\1\53\4\uffff\1\53\2\uffff\5\53\3\uffff"+
					"\1\53\1\uffff\2\53\1\52\1\uffff\13\53\2\uffff\1\53\1\uffff\3\53\4\uffff"+
					"\2\53\1\uffff\4\53\3\uffff\1\53\10\uffff\2\53\2\uffff\1\53",
			"\1\56\4\uffff\1\56\1\54\1\uffff\1\55\2\uffff\1\56\1\uffff\1\56\1\55"+
					"\2\uffff\3\56\1\uffff\3\56\1\uffff\4\56\1\55\3\56\3\uffff\1\55\2\56\1"+
					"\55\1\56\1\uffff\1\55\4\56\1\uffff\1\56\1\uffff\2\56\1\uffff\1\56\3\uffff"+
					"\1\56\1\uffff\2\56\1\uffff\1\56\2\uffff\3\56\1\uffff\3\56\1\uffff\3\56"+
					"\3\uffff\1\55\1\uffff\1\56\1\uffff\1\56\4\uffff\1\56\2\uffff\5\56\3\uffff"+
					"\1\56\1\uffff\2\56\1\55\1\uffff\13\56\2\uffff\1\56\1\uffff\3\56\4\uffff"+
					"\2\56\1\uffff\4\56\3\uffff\1\56\10\uffff\2\56\2\uffff\1\56",
			"\1\61\1\uffff\1\61\5\uffff\1\61\16\uffff\1\61\6\uffff\1\61\2\uffff\1"+
					"\61\2\uffff\1\61\44\uffff\1\61\26\uffff\1\60\1\61\30\uffff\1\57",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\62",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\52\7\uffff\1\52\24\uffff\1\53",
			"",
			"",
			"\1\56\47\uffff\1\55\7\uffff\1\55",
			"",
			"",
			"",
			"",
			"",
			"\1\30\54\uffff\1\27"
	};

	static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
	static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
	static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
	static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
	static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
	static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
	static final short[][] DFA1_transition;

	static {
		int numStates = DFA1_transitionS.length;
		DFA1_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
		}
	}

	protected class DFA1 extends DFA {

		public DFA1(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 1;
			this.eot = DFA1_eot;
			this.eof = DFA1_eof;
			this.min = DFA1_min;
			this.max = DFA1_max;
			this.accept = DFA1_accept;
			this.special = DFA1_special;
			this.transition = DFA1_transition;
		}
		@Override
		public String getDescription() {
			return "207:1: cqlStatement returns [ParsedStatement stmt] : (st1= selectStatement |st2= insertStatement |st3= updateStatement |st4= batchStatement |st5= deleteStatement |st6= useStatement |st7= truncateStatement |st8= createKeyspaceStatement |st9= createTableStatement |st10= createIndexStatement |st11= dropKeyspaceStatement |st12= dropTableStatement |st13= dropIndexStatement |st14= alterTableStatement |st15= alterKeyspaceStatement |st16= grantPermissionsStatement |st17= revokePermissionsStatement |st18= listPermissionsStatement |st19= createUserStatement |st20= alterUserStatement |st21= dropUserStatement |st22= listUsersStatement |st23= createTriggerStatement |st24= dropTriggerStatement |st25= createTypeStatement |st26= alterTypeStatement |st27= dropTypeStatement |st28= createFunctionStatement |st29= dropFunctionStatement |st30= createAggregateStatement |st31= dropAggregateStatement |st32= createRoleStatement |st33= alterRoleStatement |st34= dropRoleStatement |st35= listRolesStatement |st36= grantRoleStatement |st37= revokeRoleStatement |st38= createMaterializedViewStatement |st39= dropMaterializedViewStatement |st40= alterMaterializedViewStatement );";
		}
	}

	static final String DFA15_eotS =
			"\u0082\uffff";
	static final String DFA15_eofS =
			"\u0082\uffff";
	static final String DFA15_minS =
			"\1\6\31\41\1\uffff\1\6\5\41\1\uffff\1\26\1\uffff\1\6\1\u00b8\31\u00b7"+
					"\1\u00b8\2\u00b7\1\uffff\1\u00b7\1\u00be\1\u00b7\1\26\3\uffff\31\41\1"+
					"\uffff\1\6\1\26\31\41\3\u00b7";
	static final String DFA15_maxS =
			"\1\u00ca\31\u00be\1\uffff\1\u00ca\5\u00be\1\uffff\1\u00a9\1\uffff\1\u00ca"+
					"\1\u00bb\2\u00be\1\u00c1\27\u00be\2\u00c1\1\uffff\1\u00c1\2\u00be\1\u00a9"+
					"\3\uffff\31\u00be\1\uffff\1\u00ca\1\u00a9\31\u00be\3\u00b8";
	static final String DFA15_acceptS =
			"\32\uffff\1\2\6\uffff\1\10\1\uffff\1\1\36\uffff\1\3\4\uffff\1\5\1\6\1"+
					"\7\31\uffff\1\4\36\uffff";
	static final String DFA15_specialS =
			"\u0082\uffff}>";
	static final String[] DFA15_transitionS = {
			"\1\32\4\uffff\1\32\4\uffff\1\32\3\uffff\1\32\1\uffff\1\1\1\32\3\uffff"+
					"\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff\1\3\1\37\1\3"+
					"\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\40\1\12\1\uffff"+
					"\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1\3\3\uffff\1"+
					"\15\1\32\2\3\1\uffff\1\16\2\uffff\2\40\1\3\1\uffff\3\3\1\uffff\3\3\4"+
					"\uffff\1\32\1\3\1\uffff\1\3\1\uffff\1\32\2\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\1\uffff"+
					"\1\41\1\3\1\uffff\1\36\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\35\10\uffff\1\34\1\2\2\uffff\1\32\2\uffff\1\32\7\uffff\1\33\4\uffff"+
					"\1\32\2\uffff\1\32\6\uffff\1\32\3\uffff\1\32",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\44\1\43\2\uffff\1\43\2\uffff\1\42",
			"",
			"\1\32\4\uffff\1\32\4\uffff\1\32\3\uffff\1\32\1\uffff\1\46\1\32\3\uffff"+
					"\2\105\4\uffff\1\105\1\uffff\1\51\3\uffff\1\52\1\53\1\54\1\uffff\1\105"+
					"\1\104\1\105\1\uffff\2\105\1\76\1\55\1\uffff\1\105\1\74\1\56\4\uffff"+
					"\1\104\1\57\1\uffff\1\60\2\uffff\3\105\1\61\1\uffff\1\103\1\uffff\2\105"+
					"\1\uffff\1\105\3\uffff\1\62\1\32\2\105\1\uffff\1\63\2\uffff\1\104\1\77"+
					"\1\105\1\uffff\3\105\1\uffff\1\100\1\105\1\50\4\uffff\1\32\1\105\1\uffff"+
					"\1\105\1\uffff\1\32\2\uffff\1\105\2\uffff\5\105\3\uffff\1\105\1\uffff"+
					"\2\105\1\uffff\1\102\1\105\1\64\4\105\1\65\1\75\1\66\1\73\1\67\1\uffff"+
					"\1\32\1\105\1\uffff\1\104\1\101\1\105\4\uffff\2\105\1\uffff\1\70\1\105"+
					"\1\71\1\72\3\uffff\1\104\10\uffff\1\32\1\47\2\uffff\1\45\2\uffff\1\32"+
					"\7\uffff\1\32\4\uffff\1\32\2\uffff\1\32\6\uffff\1\32\3\uffff\1\32",
			"\1\32\43\uffff\1\32\162\uffff\1\32\2\uffff\1\32\2\uffff\1\106",
			"\1\43\43\uffff\1\43\161\uffff\1\107\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\110\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\161\uffff\1\111\1\43\2\uffff\1\43\2\uffff\1\42",
			"\1\43\43\uffff\1\43\162\uffff\1\43\2\uffff\1\43\2\uffff\1\42",
			"",
			"\1\112\4\uffff\2\114\4\uffff\1\114\1\uffff\1\115\3\uffff\1\116\1\117"+
					"\1\120\1\uffff\1\114\1\43\1\114\1\uffff\2\114\1\142\1\121\1\uffff\1\114"+
					"\1\140\1\122\4\uffff\1\43\1\123\1\uffff\1\124\2\uffff\3\114\1\125\1\uffff"+
					"\1\114\1\uffff\2\114\1\uffff\1\114\3\uffff\1\126\1\uffff\2\114\1\uffff"+
					"\1\127\2\uffff\2\43\1\114\1\uffff\3\114\1\uffff\3\114\5\uffff\1\114\1"+
					"\uffff\1\114\4\uffff\1\114\2\uffff\5\114\3\uffff\1\114\1\uffff\2\114"+
					"\2\uffff\1\114\1\130\4\114\1\131\1\141\1\132\1\137\1\133\1\uffff\1\41"+
					"\1\114\1\uffff\1\43\2\114\4\uffff\2\114\1\uffff\1\134\1\114\1\135\1\136"+
					"\3\uffff\1\43\11\uffff\1\113",
			"",
			"\1\41\4\uffff\1\41\4\uffff\1\41\3\uffff\1\41\1\uffff\2\41\3\uffff\2"+
					"\41\4\uffff\1\41\1\uffff\1\41\3\uffff\3\41\1\uffff\3\41\1\uffff\4\41"+
					"\1\uffff\3\41\4\uffff\2\41\1\uffff\1\41\2\uffff\4\41\1\uffff\1\41\1\uffff"+
					"\2\41\1\uffff\1\41\3\uffff\4\41\1\uffff\1\41\2\uffff\3\41\1\uffff\3\41"+
					"\1\uffff\3\41\4\uffff\2\41\1\uffff\1\41\1\uffff\1\41\2\uffff\1\41\2\uffff"+
					"\5\41\3\uffff\1\41\1\uffff\2\41\2\uffff\13\41\1\uffff\2\41\1\uffff\3"+
					"\41\4\uffff\2\41\1\uffff\4\41\3\uffff\1\41\10\uffff\2\41\2\uffff\1\41"+
					"\2\uffff\1\41\7\uffff\2\41\3\uffff\1\41\2\uffff\1\41\6\uffff\1\41\1\143"+
					"\2\uffff\1\41",
			"\1\144\2\uffff\1\32",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145\2\uffff\1\102",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\32\6\uffff\1\145",
			"\1\102\5\uffff\1\145",
			"\1\32\1\102\5\uffff\1\145\2\uffff\1\102",
			"\1\32\1\102\5\uffff\1\145\2\uffff\1\102",
			"",
			"\1\32\1\102\5\uffff\1\145\2\uffff\1\102",
			"\1\145",
			"\1\32\1\102\5\uffff\1\145",
			"\1\146\4\uffff\2\150\4\uffff\1\150\1\uffff\1\151\3\uffff\1\152\1\153"+
					"\1\154\1\uffff\1\150\1\32\1\150\1\uffff\2\150\1\176\1\155\1\uffff\1\150"+
					"\1\174\1\156\4\uffff\1\32\1\157\1\uffff\1\160\2\uffff\3\150\1\161\1\uffff"+
					"\1\150\1\uffff\2\150\1\uffff\1\150\3\uffff\1\162\1\uffff\2\150\1\uffff"+
					"\1\163\2\uffff\2\32\1\150\1\uffff\3\150\1\uffff\3\150\5\uffff\1\150\1"+
					"\uffff\1\150\4\uffff\1\150\2\uffff\5\150\3\uffff\1\150\1\uffff\2\150"+
					"\2\uffff\1\150\1\164\4\150\1\165\1\175\1\166\1\173\1\167\1\uffff\1\41"+
					"\1\150\1\uffff\1\32\2\150\4\uffff\2\150\1\uffff\1\170\1\150\1\171\1\172"+
					"\3\uffff\1\32\11\uffff\1\147",
			"",
			"",
			"",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"\1\43\43\uffff\1\43\161\uffff\1\41\1\43\2\uffff\1\43\2\uffff\1\43",
			"",
			"\1\102\4\uffff\1\102\4\uffff\1\102\3\uffff\1\102\2\uffff\1\102\11\uffff"+
					"\1\32\43\uffff\1\32\12\uffff\1\102\25\uffff\1\102\4\uffff\1\102\74\uffff"+
					"\1\102\3\uffff\1\102\2\uffff\1\102\7\uffff\1\102\1\32\2\uffff\1\32\1"+
					"\102\1\uffff\1\32\1\102\6\uffff\1\102\3\uffff\1\102",
			"\1\177\4\uffff\2\u0081\4\uffff\1\u0081\1\uffff\1\32\3\uffff\3\32\1\uffff"+
					"\1\u0081\1\uffff\1\u0081\1\uffff\2\u0081\2\32\1\uffff\1\u0081\2\32\5"+
					"\uffff\1\32\1\uffff\1\32\2\uffff\3\u0081\1\32\1\uffff\1\u0081\1\uffff"+
					"\2\u0081\1\uffff\1\u0081\3\uffff\1\32\1\uffff\2\u0081\1\uffff\1\32\3"+
					"\uffff\1\102\1\u0081\1\uffff\3\u0081\1\uffff\3\u0081\5\uffff\1\u0081"+
					"\1\uffff\1\u0081\4\uffff\1\u0081\2\uffff\5\u0081\3\uffff\1\u0081\1\uffff"+
					"\2\u0081\2\uffff\1\u0081\1\32\4\u0081\5\32\1\uffff\1\32\1\u0081\2\uffff"+
					"\2\u0081\4\uffff\2\u0081\1\uffff\1\32\1\u0081\2\32\15\uffff\1\u0080",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\43\uffff\1\32\161\uffff\1\41\1\32\2\uffff\1\32\2\uffff\1\32",
			"\1\32\1\102",
			"\1\32\1\102",
			"\1\32\1\102"
	};

	static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
	static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
	static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
	static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
	static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
	static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
	static final short[][] DFA15_transition;

	static {
		int numStates = DFA15_transitionS.length;
		DFA15_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
		}
	}

	protected class DFA15 extends DFA {

		public DFA15(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 15;
			this.eot = DFA15_eot;
			this.eof = DFA15_eof;
			this.min = DFA15_min;
			this.max = DFA15_max;
			this.accept = DFA15_accept;
			this.special = DFA15_special;
			this.transition = DFA15_transition;
		}
		@Override
		public String getDescription() {
			return "311:8: (c= cident |v= value | '(' ct= comparatorType ')' v= value | K_COUNT '(' '\\*' ')' | K_WRITETIME '(' c= cident ')' | K_TTL '(' c= cident ')' | K_CAST '(' sn= unaliasedSelector K_AS t= native_type ')' |f= functionName args= selectionFunctionArgs )";
		}
	}

	static final String DFA44_eotS =
			"\35\uffff";
	static final String DFA44_eofS =
			"\35\uffff";
	static final String DFA44_minS =
			"\1\26\31\105\3\uffff";
	static final String DFA44_maxS =
			"\1\u00a9\31\u00c6\3\uffff";
	static final String DFA44_acceptS =
			"\32\uffff\1\1\1\2\1\3";
	static final String DFA44_specialS =
			"\35\uffff}>";
	static final String[] DFA44_transitionS = {
			"\1\1\4\uffff\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff"+
					"\1\3\1\31\1\3\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\31"+
					"\1\12\1\uffff\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1"+
					"\3\3\uffff\1\15\1\uffff\2\3\1\uffff\1\16\2\uffff\2\31\1\3\1\uffff\3\3"+
					"\1\uffff\3\3\5\uffff\1\3\1\uffff\1\3\4\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\2\uffff"+
					"\1\3\1\uffff\1\31\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\31\11\uffff\1\2",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"\1\32\165\uffff\1\32\2\uffff\1\34\7\uffff\1\33",
			"",
			"",
			""
	};

	static final short[] DFA44_eot = DFA.unpackEncodedString(DFA44_eotS);
	static final short[] DFA44_eof = DFA.unpackEncodedString(DFA44_eofS);
	static final char[] DFA44_min = DFA.unpackEncodedStringToUnsignedChars(DFA44_minS);
	static final char[] DFA44_max = DFA.unpackEncodedStringToUnsignedChars(DFA44_maxS);
	static final short[] DFA44_accept = DFA.unpackEncodedString(DFA44_acceptS);
	static final short[] DFA44_special = DFA.unpackEncodedString(DFA44_specialS);
	static final short[][] DFA44_transition;

	static {
		int numStates = DFA44_transitionS.length;
		DFA44_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA44_transition[i] = DFA.unpackEncodedString(DFA44_transitionS[i]);
		}
	}

	protected class DFA44 extends DFA {

		public DFA44(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 44;
			this.eot = DFA44_eot;
			this.eof = DFA44_eof;
			this.min = DFA44_min;
			this.max = DFA44_max;
			this.accept = DFA44_accept;
			this.special = DFA44_special;
			this.transition = DFA44_transition;
		}
		@Override
		public String getDescription() {
			return "482:1: deleteOp returns [Operation.RawDeletion op] : (c= cident |c= cident '[' t= term ']' |c= cident '.' field= fident );";
		}
	}

	static final String DFA153_eotS =
			"\35\uffff";
	static final String DFA153_eofS =
			"\1\uffff\32\34\2\uffff";
	static final String DFA153_minS =
			"\1\26\32\u00be\2\uffff";
	static final String DFA153_maxS =
			"\1\u00a9\32\u00c0\2\uffff";
	static final String DFA153_acceptS =
			"\33\uffff\1\1\1\2";
	static final String DFA153_specialS =
			"\35\uffff}>";
	static final String[] DFA153_transitionS = {
			"\1\1\4\uffff\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff"+
					"\1\3\1\31\1\3\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\31"+
					"\1\12\1\uffff\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1"+
					"\3\3\uffff\1\15\1\uffff\2\3\1\uffff\1\16\2\uffff\2\31\1\3\1\uffff\3\3"+
					"\1\uffff\3\3\5\uffff\1\3\1\uffff\1\3\4\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\2\uffff"+
					"\1\3\1\uffff\1\31\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\31\10\uffff\1\32\1\2",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"\1\33\1\uffff\1\34",
			"",
			""
	};

	static final short[] DFA153_eot = DFA.unpackEncodedString(DFA153_eotS);
	static final short[] DFA153_eof = DFA.unpackEncodedString(DFA153_eofS);
	static final char[] DFA153_min = DFA.unpackEncodedStringToUnsignedChars(DFA153_minS);
	static final char[] DFA153_max = DFA.unpackEncodedStringToUnsignedChars(DFA153_maxS);
	static final short[] DFA153_accept = DFA.unpackEncodedString(DFA153_acceptS);
	static final short[] DFA153_special = DFA.unpackEncodedString(DFA153_specialS);
	static final short[][] DFA153_transition;

	static {
		int numStates = DFA153_transitionS.length;
		DFA153_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA153_transition[i] = DFA.unpackEncodedString(DFA153_transitionS[i]);
		}
	}

	protected class DFA153 extends DFA {

		public DFA153(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 153;
			this.eot = DFA153_eot;
			this.eof = DFA153_eof;
			this.min = DFA153_min;
			this.max = DFA153_max;
			this.accept = DFA153_accept;
			this.special = DFA153_special;
			this.transition = DFA153_transition;
		}
		@Override
		public String getDescription() {
			return "1210:7: ( ksName[name] '.' )?";
		}
	}

	static final String DFA154_eotS =
			"\35\uffff";
	static final String DFA154_eofS =
			"\1\uffff\32\34\2\uffff";
	static final String DFA154_minS =
			"\1\26\32\32\2\uffff";
	static final String DFA154_maxS =
			"\1\u00a9\32\u00c0\2\uffff";
	static final String DFA154_acceptS =
			"\33\uffff\1\1\1\2";
	static final String DFA154_specialS =
			"\35\uffff}>";
	static final String[] DFA154_transitionS = {
			"\1\1\4\uffff\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff"+
					"\1\3\1\31\1\3\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\31"+
					"\1\12\1\uffff\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1"+
					"\3\3\uffff\1\15\1\uffff\2\3\1\uffff\1\16\2\uffff\2\31\1\3\1\uffff\3\3"+
					"\1\uffff\3\3\5\uffff\1\3\1\uffff\1\3\4\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\2\uffff"+
					"\1\3\1\uffff\1\31\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\31\10\uffff\1\32\1\2",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"\1\34\2\uffff\2\34\2\uffff\1\34\33\uffff\1\34\7\uffff\1\34\5\uffff\1"+
					"\34\13\uffff\1\34\6\uffff\1\34\11\uffff\1\34\3\uffff\1\34\3\uffff\1\34"+
					"\2\uffff\1\34\2\uffff\2\34\6\uffff\1\34\13\uffff\1\34\14\uffff\1\34\5"+
					"\uffff\2\34\30\uffff\1\34\6\uffff\1\33\1\uffff\1\34",
			"",
			""
	};

	static final short[] DFA154_eot = DFA.unpackEncodedString(DFA154_eotS);
	static final short[] DFA154_eof = DFA.unpackEncodedString(DFA154_eofS);
	static final char[] DFA154_min = DFA.unpackEncodedStringToUnsignedChars(DFA154_minS);
	static final char[] DFA154_max = DFA.unpackEncodedStringToUnsignedChars(DFA154_maxS);
	static final short[] DFA154_accept = DFA.unpackEncodedString(DFA154_acceptS);
	static final short[] DFA154_special = DFA.unpackEncodedString(DFA154_specialS);
	static final short[][] DFA154_transition;

	static {
		int numStates = DFA154_transitionS.length;
		DFA154_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA154_transition[i] = DFA.unpackEncodedString(DFA154_transitionS[i]);
		}
	}

	protected class DFA154 extends DFA {

		public DFA154(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 154;
			this.eot = DFA154_eot;
			this.eof = DFA154_eof;
			this.min = DFA154_min;
			this.max = DFA154_max;
			this.accept = DFA154_accept;
			this.special = DFA154_special;
			this.transition = DFA154_transition;
		}
		@Override
		public String getDescription() {
			return "1215:7: ( ksName[name] '.' )?";
		}
	}

	static final String DFA172_eotS =
			"\43\uffff";
	static final String DFA172_eofS =
			"\43\uffff";
	static final String DFA172_minS =
			"\1\6\2\uffff\1\6\4\uffff\31\u00b7\1\u00be\1\uffff";
	static final String DFA172_maxS =
			"\1\u00ca\2\uffff\1\u00cb\4\uffff\32\u00bf\1\uffff";
	static final String DFA172_acceptS =
			"\1\uffff\1\1\1\2\1\uffff\1\4\1\5\1\6\1\7\32\uffff\1\3";
	static final String DFA172_specialS =
			"\43\uffff}>";
	static final String[] DFA172_transitionS = {
			"\1\1\4\uffff\1\1\4\uffff\1\1\3\uffff\1\1\2\uffff\1\1\70\uffff\1\1\25"+
					"\uffff\1\1\4\uffff\1\5\74\uffff\1\7\3\uffff\1\1\2\uffff\1\1\7\uffff\1"+
					"\4\4\uffff\1\1\2\uffff\1\6\6\uffff\1\2\3\uffff\1\3",
			"",
			"",
			"\1\2\4\uffff\1\2\4\uffff\1\2\3\uffff\1\2\1\uffff\1\10\1\2\3\uffff\2"+
					"\12\4\uffff\1\12\1\uffff\1\13\3\uffff\1\14\1\15\1\16\1\uffff\1\12\1\41"+
					"\1\12\1\uffff\2\12\1\40\1\17\1\uffff\1\12\1\36\1\20\4\uffff\1\41\1\21"+
					"\1\uffff\1\22\2\uffff\3\12\1\23\1\uffff\1\12\1\uffff\2\12\1\uffff\1\12"+
					"\3\uffff\1\24\1\2\2\12\1\uffff\1\25\2\uffff\2\41\1\12\1\uffff\3\12\1"+
					"\uffff\3\12\4\uffff\1\2\1\12\1\uffff\1\12\1\uffff\1\2\2\uffff\1\12\2"+
					"\uffff\5\12\3\uffff\1\12\1\uffff\2\12\2\uffff\1\12\1\26\4\12\1\27\1\37"+
					"\1\30\1\35\1\31\1\uffff\1\2\1\12\1\uffff\1\41\2\12\4\uffff\2\12\1\uffff"+
					"\1\32\1\12\1\33\1\34\3\uffff\1\41\10\uffff\1\2\1\11\2\uffff\1\2\2\uffff"+
					"\1\2\7\uffff\1\2\4\uffff\1\2\2\uffff\1\2\6\uffff\1\2\3\uffff\2\2",
			"",
			"",
			"",
			"",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\6\uffff\1\2\1\42",
			"\1\2\1\42",
			""
	};

	static final short[] DFA172_eot = DFA.unpackEncodedString(DFA172_eotS);
	static final short[] DFA172_eof = DFA.unpackEncodedString(DFA172_eofS);
	static final char[] DFA172_min = DFA.unpackEncodedStringToUnsignedChars(DFA172_minS);
	static final char[] DFA172_max = DFA.unpackEncodedStringToUnsignedChars(DFA172_maxS);
	static final short[] DFA172_accept = DFA.unpackEncodedString(DFA172_acceptS);
	static final short[] DFA172_special = DFA.unpackEncodedString(DFA172_specialS);
	static final short[][] DFA172_transition;

	static {
		int numStates = DFA172_transitionS.length;
		DFA172_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA172_transition[i] = DFA.unpackEncodedString(DFA172_transitionS[i]);
		}
	}

	protected class DFA172 extends DFA {

		public DFA172(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 172;
			this.eot = DFA172_eot;
			this.eof = DFA172_eof;
			this.min = DFA172_min;
			this.max = DFA172_max;
			this.accept = DFA172_accept;
			this.special = DFA172_special;
			this.transition = DFA172_transition;
		}
		@Override
		public String getDescription() {
			return "1305:1: value returns [Term.Raw value] : (c= constant |l= collectionLiteral |u= usertypeLiteral |t= tupleLiteral | K_NULL | ':' id= noncol_ident | QMARK );";
		}
	}

	static final String DFA174_eotS =
			"\34\uffff";
	static final String DFA174_eofS =
			"\1\uffff\31\33\2\uffff";
	static final String DFA174_minS =
			"\1\26\31\u00b7\2\uffff";
	static final String DFA174_maxS =
			"\1\u00a9\31\u00c0\2\uffff";
	static final String DFA174_acceptS =
			"\32\uffff\1\1\1\2";
	static final String DFA174_specialS =
			"\34\uffff}>";
	static final String[] DFA174_transitionS = {
			"\1\1\4\uffff\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff"+
					"\1\3\1\32\1\3\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\32"+
					"\1\12\1\uffff\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1"+
					"\3\3\uffff\1\15\1\uffff\2\3\1\uffff\1\16\2\uffff\2\32\1\3\1\uffff\3\3"+
					"\1\uffff\3\3\5\uffff\1\3\1\uffff\1\3\4\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\1\uffff"+
					"\1\33\1\3\1\uffff\1\32\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\32\10\uffff\1\32\1\2",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"\1\33\6\uffff\1\32\1\uffff\1\33",
			"",
			""
	};

	static final short[] DFA174_eot = DFA.unpackEncodedString(DFA174_eotS);
	static final short[] DFA174_eof = DFA.unpackEncodedString(DFA174_eofS);
	static final char[] DFA174_min = DFA.unpackEncodedStringToUnsignedChars(DFA174_minS);
	static final char[] DFA174_max = DFA.unpackEncodedStringToUnsignedChars(DFA174_maxS);
	static final short[] DFA174_accept = DFA.unpackEncodedString(DFA174_acceptS);
	static final short[] DFA174_special = DFA.unpackEncodedString(DFA174_specialS);
	static final short[][] DFA174_transition;

	static {
		int numStates = DFA174_transitionS.length;
		DFA174_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA174_transition[i] = DFA.unpackEncodedString(DFA174_transitionS[i]);
		}
	}

	protected class DFA174 extends DFA {

		public DFA174(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 174;
			this.eot = DFA174_eot;
			this.eof = DFA174_eof;
			this.min = DFA174_min;
			this.max = DFA174_max;
			this.accept = DFA174_accept;
			this.special = DFA174_special;
			this.transition = DFA174_transition;
		}
		@Override
		public String getDescription() {
			return "1322:7: (ks= keyspaceName '.' )?";
		}
	}

	static final String DFA176_eotS =
			"\72\uffff";
	static final String DFA176_eofS =
			"\72\uffff";
	static final String DFA176_minS =
			"\1\26\31\u00b7\1\u00be\1\u00b7\1\u00be\1\26\1\6\31\u00b7\2\uffff";
	static final String DFA176_maxS =
			"\1\u00a9\32\u00be\1\u00b7\1\u00be\1\u00a9\1\u00ca\31\u00b7\2\uffff";
	static final String DFA176_acceptS =
			"\70\uffff\1\1\1\2";
	static final String DFA176_specialS =
			"\72\uffff}>";
	static final String[] DFA176_transitionS = {
			"\1\1\4\uffff\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff"+
					"\1\3\1\34\1\3\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\34"+
					"\1\12\1\uffff\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1"+
					"\3\3\uffff\1\15\1\uffff\2\3\1\uffff\1\16\2\uffff\2\34\1\3\1\uffff\3\3"+
					"\1\uffff\3\3\5\uffff\1\3\1\uffff\1\3\4\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\1\uffff"+
					"\1\33\1\3\1\uffff\1\34\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\34\10\uffff\1\32\1\2",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\36\6\uffff\1\35",
			"\1\35",
			"\1\36",
			"\1\35",
			"\1\37\4\uffff\2\41\4\uffff\1\41\1\uffff\1\42\3\uffff\1\43\1\44\1\45"+
					"\1\uffff\1\41\1\uffff\1\41\1\uffff\2\41\1\67\1\46\1\uffff\1\41\1\65\1"+
					"\47\5\uffff\1\50\1\uffff\1\51\2\uffff\3\41\1\52\1\uffff\1\41\1\uffff"+
					"\2\41\1\uffff\1\41\3\uffff\1\53\1\uffff\2\41\1\uffff\1\54\4\uffff\1\41"+
					"\1\uffff\3\41\1\uffff\3\41\5\uffff\1\41\1\uffff\1\41\4\uffff\1\41\2\uffff"+
					"\5\41\3\uffff\1\41\1\uffff\2\41\2\uffff\1\41\1\55\4\41\1\56\1\66\1\57"+
					"\1\64\1\60\1\uffff\1\33\1\41\2\uffff\2\41\4\uffff\2\41\1\uffff\1\61\1"+
					"\41\1\62\1\63\15\uffff\1\40",
			"\1\71\4\uffff\1\71\4\uffff\1\71\3\uffff\1\71\1\uffff\2\71\3\uffff\2"+
					"\71\4\uffff\1\71\1\uffff\1\71\3\uffff\3\71\1\uffff\3\71\1\uffff\4\71"+
					"\1\uffff\3\71\4\uffff\2\71\1\uffff\1\71\2\uffff\4\71\1\uffff\1\71\1\uffff"+
					"\2\71\1\uffff\1\71\3\uffff\4\71\1\uffff\1\71\2\uffff\3\71\1\uffff\3\71"+
					"\1\uffff\3\71\4\uffff\2\71\1\uffff\1\71\1\uffff\1\71\2\uffff\1\71\2\uffff"+
					"\5\71\3\uffff\1\71\1\uffff\2\71\2\uffff\13\71\1\uffff\2\71\1\uffff\3"+
					"\71\4\uffff\2\71\1\uffff\4\71\3\uffff\1\71\10\uffff\2\71\2\uffff\1\71"+
					"\2\uffff\1\71\7\uffff\1\71\1\70\3\uffff\1\71\2\uffff\1\71\6\uffff\1\71"+
					"\3\uffff\1\71",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"\1\36",
			"",
			""
	};

	static final short[] DFA176_eot = DFA.unpackEncodedString(DFA176_eotS);
	static final short[] DFA176_eof = DFA.unpackEncodedString(DFA176_eofS);
	static final char[] DFA176_min = DFA.unpackEncodedStringToUnsignedChars(DFA176_minS);
	static final char[] DFA176_max = DFA.unpackEncodedStringToUnsignedChars(DFA176_maxS);
	static final short[] DFA176_accept = DFA.unpackEncodedString(DFA176_acceptS);
	static final short[] DFA176_special = DFA.unpackEncodedString(DFA176_specialS);
	static final short[][] DFA176_transition;

	static {
		int numStates = DFA176_transitionS.length;
		DFA176_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA176_transition[i] = DFA.unpackEncodedString(DFA176_transitionS[i]);
		}
	}

	protected class DFA176 extends DFA {

		public DFA176(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 176;
			this.eot = DFA176_eot;
			this.eof = DFA176_eof;
			this.min = DFA176_min;
			this.max = DFA176_max;
			this.accept = DFA176_accept;
			this.special = DFA176_special;
			this.transition = DFA176_transition;
		}
		@Override
		public String getDescription() {
			return "1333:1: function returns [Term.Raw t] : (f= functionName '(' ')' |f= functionName '(' args= functionArgs ')' );";
		}
	}

	static final String DFA178_eotS =
			"\110\uffff";
	static final String DFA178_eofS =
			"\3\uffff\1\1\42\uffff\1\1\7\uffff\32\42";
	static final String DFA178_minS =
			"\1\6\1\uffff\1\6\1\35\1\uffff\1\u00b8\31\u00b7\1\u00b8\2\u00b7\1\uffff"+
					"\1\u00b7\1\u00be\1\u00b7\1\6\1\26\1\6\1\52\1\161\3\u00b7\32\35";
	static final String DFA178_maxS =
			"\1\u00ca\1\uffff\1\u00ca\1\u00cb\1\uffff\1\u00bb\2\u00be\1\u00c1\27\u00be"+
					"\2\u00c1\1\uffff\1\u00c1\2\u00be\1\u00cb\1\u00a9\1\u00ca\2\u00be\3\u00b8"+
					"\32\u00cb";
	static final String DFA178_acceptS =
			"\1\uffff\1\1\2\uffff\1\2\35\uffff\1\3\45\uffff";
	static final String DFA178_specialS =
			"\110\uffff}>";
	static final String[] DFA178_transitionS = {
			"\1\1\4\uffff\1\1\4\uffff\1\1\3\uffff\1\1\1\uffff\1\4\1\1\3\uffff\2\4"+
					"\4\uffff\1\4\1\uffff\1\4\3\uffff\3\4\1\uffff\3\4\1\uffff\4\4\1\uffff"+
					"\3\4\4\uffff\2\4\1\uffff\1\4\2\uffff\4\4\1\uffff\1\4\1\uffff\2\4\1\uffff"+
					"\1\4\3\uffff\1\4\1\1\2\4\1\uffff\1\4\2\uffff\3\4\1\uffff\3\4\1\uffff"+
					"\3\4\4\uffff\1\1\1\4\1\uffff\1\4\1\uffff\1\1\2\uffff\1\4\2\uffff\5\4"+
					"\3\uffff\1\4\1\uffff\2\4\2\uffff\13\4\1\uffff\2\4\1\uffff\3\4\4\uffff"+
					"\2\4\1\uffff\4\4\3\uffff\1\4\10\uffff\1\3\1\4\2\uffff\1\1\2\uffff\1\1"+
					"\7\uffff\1\2\4\uffff\1\1\2\uffff\1\1\6\uffff\1\1\3\uffff\1\1",
			"",
			"\1\1\4\uffff\1\1\4\uffff\1\1\3\uffff\1\1\1\uffff\1\6\1\1\3\uffff\2\45"+
					"\4\uffff\1\45\1\uffff\1\11\3\uffff\1\12\1\13\1\14\1\uffff\1\45\1\44\1"+
					"\45\1\uffff\2\45\1\36\1\15\1\uffff\1\45\1\34\1\16\4\uffff\1\44\1\17\1"+
					"\uffff\1\20\2\uffff\3\45\1\21\1\uffff\1\43\1\uffff\2\45\1\uffff\1\45"+
					"\3\uffff\1\22\1\1\2\45\1\uffff\1\23\2\uffff\1\44\1\37\1\45\1\uffff\3"+
					"\45\1\uffff\1\40\1\45\1\10\4\uffff\1\1\1\45\1\uffff\1\45\1\uffff\1\1"+
					"\2\uffff\1\45\2\uffff\5\45\3\uffff\1\45\1\uffff\2\45\1\uffff\1\42\1\45"+
					"\1\24\4\45\1\25\1\35\1\26\1\33\1\27\1\uffff\1\1\1\45\1\uffff\1\44\1\41"+
					"\1\45\4\uffff\2\45\1\uffff\1\30\1\45\1\31\1\32\3\uffff\1\44\10\uffff"+
					"\1\1\1\7\2\uffff\1\5\2\uffff\1\1\7\uffff\1\1\4\uffff\1\1\2\uffff\1\1"+
					"\6\uffff\1\1\3\uffff\1\1",
			"\1\1\1\uffff\2\1\27\uffff\1\1\22\uffff\2\1\6\uffff\1\1\12\uffff\1\1"+
					"\21\uffff\1\1\2\uffff\1\1\2\uffff\1\1\34\uffff\1\1\11\uffff\1\1\32\uffff"+
					"\2\1\1\uffff\1\1\2\uffff\1\4\2\1\7\uffff\1\1\2\uffff\1\1",
			"",
			"\1\46\2\uffff\1\1",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47\2\uffff\1\42",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\1\6\uffff\1\47",
			"\1\42\5\uffff\1\47",
			"\1\1\1\42\5\uffff\1\47\2\uffff\1\42",
			"\1\1\1\42\5\uffff\1\47\2\uffff\1\42",
			"",
			"\1\1\1\42\5\uffff\1\47\2\uffff\1\42",
			"\1\47",
			"\1\1\1\42\5\uffff\1\47",
			"\1\42\4\uffff\1\42\4\uffff\1\42\3\uffff\1\42\1\uffff\2\42\3\uffff\2"+
					"\42\1\1\1\uffff\2\1\1\42\1\uffff\1\42\3\uffff\3\42\1\uffff\3\42\1\uffff"+
					"\4\42\1\uffff\3\42\1\uffff\1\1\2\uffff\2\42\1\uffff\1\42\2\uffff\4\42"+
					"\1\uffff\1\42\1\uffff\2\42\1\uffff\1\51\1\1\2\uffff\4\42\1\1\1\42\2\uffff"+
					"\3\42\1\uffff\3\42\1\1\3\42\4\uffff\2\42\1\uffff\1\42\1\uffff\1\42\2"+
					"\uffff\1\42\1\uffff\1\1\2\42\1\52\2\42\1\1\2\uffff\1\42\1\uffff\2\42"+
					"\2\uffff\13\42\1\uffff\2\42\1\uffff\3\42\2\uffff\1\1\1\uffff\2\42\1\uffff"+
					"\4\42\1\uffff\1\1\1\uffff\1\42\10\uffff\2\42\2\uffff\1\42\2\uffff\1\42"+
					"\7\uffff\1\42\2\1\1\uffff\1\1\1\42\2\uffff\1\50\1\1\5\uffff\1\42\1\uffff"+
					"\1\1\1\uffff\1\42\1\1",
			"\1\53\4\uffff\2\55\4\uffff\1\55\1\uffff\1\1\3\uffff\3\1\1\uffff\1\55"+
					"\1\uffff\1\55\1\uffff\2\55\2\1\1\uffff\1\55\2\1\5\uffff\1\1\1\uffff\1"+
					"\1\2\uffff\3\55\1\1\1\uffff\1\55\1\uffff\2\55\1\uffff\1\55\3\uffff\1"+
					"\1\1\uffff\2\55\1\uffff\1\1\3\uffff\1\42\1\55\1\uffff\3\55\1\uffff\3"+
					"\55\5\uffff\1\55\1\uffff\1\55\4\uffff\1\55\2\uffff\5\55\3\uffff\1\55"+
					"\1\uffff\2\55\2\uffff\1\55\1\1\4\55\5\1\1\uffff\1\1\1\55\2\uffff\2\55"+
					"\4\uffff\2\55\1\uffff\1\1\1\55\2\1\15\uffff\1\54",
			"\1\1\4\uffff\1\1\4\uffff\1\1\3\uffff\1\1\1\uffff\1\56\1\1\3\uffff\2"+
					"\60\4\uffff\1\60\1\uffff\1\61\3\uffff\1\62\1\63\1\64\1\uffff\1\60\1\107"+
					"\1\60\1\uffff\2\60\1\106\1\65\1\uffff\1\60\1\104\1\66\4\uffff\1\107\1"+
					"\67\1\uffff\1\70\2\uffff\3\60\1\71\1\uffff\1\60\1\uffff\2\60\1\uffff"+
					"\1\60\3\uffff\1\72\1\1\2\60\1\uffff\1\73\2\uffff\2\107\1\60\1\uffff\3"+
					"\60\1\uffff\3\60\4\uffff\1\1\1\60\1\uffff\1\60\1\uffff\1\1\2\uffff\1"+
					"\60\2\uffff\5\60\3\uffff\1\60\1\uffff\2\60\2\uffff\1\60\1\74\4\60\1\75"+
					"\1\105\1\76\1\103\1\77\1\uffff\1\1\1\60\1\uffff\1\107\2\60\4\uffff\2"+
					"\60\1\uffff\1\100\1\60\1\101\1\102\3\uffff\1\107\10\uffff\1\1\1\57\2"+
					"\uffff\1\1\2\uffff\1\1\7\uffff\1\1\4\uffff\1\1\2\uffff\1\1\6\uffff\1"+
					"\1\3\uffff\1\1",
			"\1\1\u008c\uffff\1\42\6\uffff\1\42",
			"\1\1\105\uffff\1\42\6\uffff\1\42",
			"\1\1\1\42",
			"\1\1\1\42",
			"\1\1\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\31\uffff\1\1\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2"+
					"\uffff\1\42",
			"\1\42\1\uffff\2\42\27\uffff\1\42\22\uffff\2\42\6\uffff\1\42\12\uffff"+
					"\1\42\21\uffff\1\42\2\uffff\1\42\2\uffff\1\42\34\uffff\1\42\11\uffff"+
					"\1\42\32\uffff\2\42\1\uffff\1\42\2\uffff\1\1\2\42\7\uffff\1\42\2\uffff"+
					"\1\42"
	};

	static final short[] DFA178_eot = DFA.unpackEncodedString(DFA178_eotS);
	static final short[] DFA178_eof = DFA.unpackEncodedString(DFA178_eofS);
	static final char[] DFA178_min = DFA.unpackEncodedStringToUnsignedChars(DFA178_minS);
	static final char[] DFA178_max = DFA.unpackEncodedStringToUnsignedChars(DFA178_maxS);
	static final short[] DFA178_accept = DFA.unpackEncodedString(DFA178_acceptS);
	static final short[] DFA178_special = DFA.unpackEncodedString(DFA178_specialS);
	static final short[][] DFA178_transition;

	static {
		int numStates = DFA178_transitionS.length;
		DFA178_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA178_transition[i] = DFA.unpackEncodedString(DFA178_transitionS[i]);
		}
	}

	protected class DFA178 extends DFA {

		public DFA178(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 178;
			this.eot = DFA178_eot;
			this.eof = DFA178_eof;
			this.min = DFA178_min;
			this.max = DFA178_max;
			this.accept = DFA178_accept;
			this.special = DFA178_special;
			this.transition = DFA178_transition;
		}
		@Override
		public String getDescription() {
			return "1343:1: term returns [Term.Raw term] : (v= value |f= function | '(' c= comparatorType ')' t= term );";
		}
	}

	static final String DFA181_eotS =
			"\36\uffff";
	static final String DFA181_eofS =
			"\36\uffff";
	static final String DFA181_minS =
			"\1\6\1\uffff\32\27\2\uffff";
	static final String DFA181_maxS =
			"\1\u00ca\1\uffff\32\u00be\2\uffff";
	static final String DFA181_acceptS =
			"\1\uffff\1\1\32\uffff\1\2\1\3";
	static final String DFA181_specialS =
			"\36\uffff}>";
	static final String[] DFA181_transitionS = {
			"\1\1\4\uffff\1\1\4\uffff\1\1\3\uffff\1\1\1\uffff\1\2\1\1\3\uffff\2\4"+
					"\4\uffff\1\4\1\uffff\1\5\3\uffff\1\6\1\7\1\10\1\uffff\1\4\1\33\1\4\1"+
					"\uffff\2\4\1\32\1\11\1\uffff\1\4\1\30\1\12\4\uffff\1\33\1\13\1\uffff"+
					"\1\14\2\uffff\3\4\1\15\1\uffff\1\4\1\uffff\2\4\1\uffff\1\4\3\uffff\1"+
					"\16\1\1\2\4\1\uffff\1\17\2\uffff\2\33\1\4\1\uffff\3\4\1\uffff\3\4\4\uffff"+
					"\1\1\1\4\1\uffff\1\4\1\uffff\1\1\2\uffff\1\4\2\uffff\5\4\3\uffff\1\4"+
					"\1\uffff\2\4\2\uffff\1\4\1\20\4\4\1\21\1\31\1\22\1\27\1\23\1\uffff\1"+
					"\1\1\4\1\uffff\1\33\2\4\4\uffff\2\4\1\uffff\1\24\1\4\1\25\1\26\3\uffff"+
					"\1\33\10\uffff\1\1\1\3\2\uffff\1\1\2\uffff\1\1\7\uffff\1\1\4\uffff\1"+
					"\1\2\uffff\1\1\6\uffff\1\1\3\uffff\1\1",
			"",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u009f\uffff\1\1\1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"\1\35\u00a1\uffff\1\34\2\uffff\1\34\1\uffff\1\1",
			"",
			""
	};

	static final short[] DFA181_eot = DFA.unpackEncodedString(DFA181_eotS);
	static final short[] DFA181_eof = DFA.unpackEncodedString(DFA181_eofS);
	static final char[] DFA181_min = DFA.unpackEncodedStringToUnsignedChars(DFA181_minS);
	static final char[] DFA181_max = DFA.unpackEncodedStringToUnsignedChars(DFA181_maxS);
	static final short[] DFA181_accept = DFA.unpackEncodedString(DFA181_acceptS);
	static final short[] DFA181_special = DFA.unpackEncodedString(DFA181_specialS);
	static final short[][] DFA181_transition;

	static {
		int numStates = DFA181_transitionS.length;
		DFA181_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA181_transition[i] = DFA.unpackEncodedString(DFA181_transitionS[i]);
		}
	}

	protected class DFA181 extends DFA {

		public DFA181(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 181;
			this.eot = DFA181_eot;
			this.eof = DFA181_eof;
			this.min = DFA181_min;
			this.max = DFA181_max;
			this.accept = DFA181_accept;
			this.special = DFA181_special;
			this.transition = DFA181_transition;
		}
		@Override
		public String getDescription() {
			return "1360:1: normalColumnOperation[List<Pair<ColumnDefinition.Raw, Operation.RawUpdate>> operations, ColumnDefinition.Raw key] : (t= term ( '+' c= cident )? |c= cident sig= ( '+' | '-' ) t= term |c= cident i= INTEGER );";
		}
	}

	static final String DFA189_eotS =
			"\35\uffff";
	static final String DFA189_eofS =
			"\35\uffff";
	static final String DFA189_minS =
			"\1\26\31\u00c3\1\6\2\uffff";
	static final String DFA189_maxS =
			"\1\u00a9\31\u00c3\1\u00ca\2\uffff";
	static final String DFA189_acceptS =
			"\33\uffff\1\1\1\2";
	static final String DFA189_specialS =
			"\35\uffff}>";
	static final String[] DFA189_transitionS = {
			"\1\1\4\uffff\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff"+
					"\1\3\1\31\1\3\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\31"+
					"\1\12\1\uffff\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1"+
					"\3\3\uffff\1\15\1\uffff\2\3\1\uffff\1\16\2\uffff\2\31\1\3\1\uffff\3\3"+
					"\1\uffff\3\3\5\uffff\1\3\1\uffff\1\3\4\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\2\uffff"+
					"\1\3\1\uffff\1\31\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\31\11\uffff\1\2",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\32",
			"\1\33\4\uffff\1\33\4\uffff\1\33\3\uffff\1\33\2\uffff\1\33\3\uffff\2"+
					"\33\4\uffff\1\33\1\uffff\1\33\3\uffff\3\33\1\uffff\3\33\1\uffff\4\33"+
					"\1\uffff\3\33\4\uffff\2\33\1\uffff\1\33\2\uffff\4\33\1\uffff\1\33\1\uffff"+
					"\2\33\1\uffff\1\33\3\uffff\4\33\1\uffff\1\33\2\uffff\3\33\1\uffff\3\33"+
					"\1\uffff\3\33\4\uffff\2\33\1\uffff\1\33\4\uffff\1\33\2\uffff\5\33\3\uffff"+
					"\1\33\1\uffff\2\33\2\uffff\13\33\2\uffff\1\33\1\uffff\3\33\4\uffff\2"+
					"\33\1\uffff\4\33\3\uffff\1\33\14\uffff\1\33\2\uffff\1\33\14\uffff\1\33"+
					"\15\uffff\1\34",
			"",
			""
	};

	static final short[] DFA189_eot = DFA.unpackEncodedString(DFA189_eotS);
	static final short[] DFA189_eof = DFA.unpackEncodedString(DFA189_eofS);
	static final char[] DFA189_min = DFA.unpackEncodedStringToUnsignedChars(DFA189_minS);
	static final char[] DFA189_max = DFA.unpackEncodedStringToUnsignedChars(DFA189_maxS);
	static final short[] DFA189_accept = DFA.unpackEncodedString(DFA189_acceptS);
	static final short[] DFA189_special = DFA.unpackEncodedString(DFA189_specialS);
	static final short[][] DFA189_transition;

	static {
		int numStates = DFA189_transitionS.length;
		DFA189_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA189_transition[i] = DFA.unpackEncodedString(DFA189_transitionS[i]);
		}
	}

	protected class DFA189 extends DFA {

		public DFA189(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 189;
			this.eot = DFA189_eot;
			this.eof = DFA189_eof;
			this.min = DFA189_min;
			this.max = DFA189_max;
			this.accept = DFA189_accept;
			this.special = DFA189_special;
			this.transition = DFA189_transition;
		}
		@Override
		public String getDescription() {
			return "1440:1: property[PropertyDefinitions props] : (k= noncol_ident '=' simple= propertyValue |k= noncol_ident '=' map= mapLiteral );";
		}
	}

	static final String DFA195_eotS =
			"\77\uffff";
	static final String DFA195_eofS =
			"\77\uffff";
	static final String DFA195_minS =
			"\1\26\31\60\1\uffff\1\26\3\uffff\1\u00a8\2\uffff\31\60\4\uffff";
	static final String DFA195_maxS =
			"\1\u00b7\31\u00c6\1\uffff\1\u00b7\3\uffff\1\u00bf\2\uffff\31\u00c6\4\uffff";
	static final String DFA195_acceptS =
			"\32\uffff\1\4\1\uffff\1\1\1\2\1\3\1\uffff\1\7\1\10\31\uffff\1\12\1\5\1"+
					"\6\1\11";
	static final String DFA195_specialS =
			"\77\uffff}>";
	static final String[] DFA195_transitionS = {
			"\1\1\4\uffff\2\3\4\uffff\1\3\1\uffff\1\4\3\uffff\1\5\1\6\1\7\1\uffff"+
					"\1\3\1\31\1\3\1\uffff\2\3\1\31\1\10\1\uffff\1\3\1\27\1\11\4\uffff\1\31"+
					"\1\12\1\uffff\1\13\2\uffff\3\3\1\14\1\uffff\1\3\1\uffff\2\3\1\uffff\1"+
					"\3\3\uffff\1\15\1\uffff\2\3\1\uffff\1\16\2\uffff\2\31\1\3\1\uffff\3\3"+
					"\1\uffff\3\3\5\uffff\1\3\1\uffff\1\3\4\uffff\1\3\2\uffff\5\3\3\uffff"+
					"\1\3\1\uffff\2\3\2\uffff\1\3\1\17\4\3\1\20\1\30\1\21\1\26\1\22\1\uffff"+
					"\1\32\1\3\1\uffff\1\31\2\3\4\uffff\2\3\1\uffff\1\23\1\3\1\24\1\25\3\uffff"+
					"\1\31\11\uffff\1\2\15\uffff\1\33",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"\1\40\34\uffff\1\37\10\uffff\1\36\6\uffff\1\35\130\uffff\1\34\12\uffff"+
					"\5\34\1\41",
			"",
			"\1\42\4\uffff\2\44\4\uffff\1\44\1\uffff\1\45\3\uffff\1\46\1\47\1\50"+
					"\1\uffff\1\44\1\72\1\44\1\uffff\2\44\1\72\1\51\1\uffff\1\44\1\70\1\52"+
					"\4\uffff\1\72\1\53\1\uffff\1\54\2\uffff\3\44\1\55\1\uffff\1\44\1\uffff"+
					"\2\44\1\uffff\1\44\3\uffff\1\56\1\uffff\2\44\1\uffff\1\57\2\uffff\2\72"+
					"\1\44\1\uffff\3\44\1\uffff\3\44\5\uffff\1\44\1\uffff\1\44\4\uffff\1\44"+
					"\2\uffff\5\44\3\uffff\1\44\1\uffff\2\44\2\uffff\1\44\1\60\4\44\1\61\1"+
					"\71\1\62\1\67\1\63\1\uffff\1\73\1\44\1\uffff\1\72\2\44\4\uffff\2\44\1"+
					"\uffff\1\64\1\44\1\65\1\66\3\uffff\1\72\11\uffff\1\43\15\uffff\1\73",
			"",
			"",
			"",
			"\1\74\16\uffff\1\75\7\uffff\1\74",
			"",
			"",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"\1\73\34\uffff\1\73\10\uffff\1\73\6\uffff\1\73\130\uffff\1\73\1\uffff"+
					"\1\76\2\uffff\1\76\5\uffff\6\73",
			"",
			"",
			"",
			""
	};

	static final short[] DFA195_eot = DFA.unpackEncodedString(DFA195_eotS);
	static final short[] DFA195_eof = DFA.unpackEncodedString(DFA195_eofS);
	static final char[] DFA195_min = DFA.unpackEncodedStringToUnsignedChars(DFA195_minS);
	static final char[] DFA195_max = DFA.unpackEncodedStringToUnsignedChars(DFA195_maxS);
	static final short[] DFA195_accept = DFA.unpackEncodedString(DFA195_acceptS);
	static final short[] DFA195_special = DFA.unpackEncodedString(DFA195_specialS);
	static final short[][] DFA195_transition;

	static {
		int numStates = DFA195_transitionS.length;
		DFA195_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA195_transition[i] = DFA.unpackEncodedString(DFA195_transitionS[i]);
		}
	}

	protected class DFA195 extends DFA {

		public DFA195(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 195;
			this.eot = DFA195_eot;
			this.eof = DFA195_eof;
			this.min = DFA195_min;
			this.max = DFA195_max;
			this.accept = DFA195_accept;
			this.special = DFA195_special;
			this.transition = DFA195_transition;
		}
		@Override
		public String getDescription() {
			return "1459:1: relation[WhereClause.Builder clauses] : (name= cident type= relationType t= term |name= cident K_LIKE t= term |name= cident K_IS K_NOT K_NULL | K_TOKEN l= tupleOfIdentifiers type= relationType t= term |name= cident K_IN marker= inMarker |name= cident K_IN inValues= singleColumnInValues |name= cident K_CONTAINS ( K_KEY )? t= term |name= cident '[' key= term ']' type= relationType t= term |ids= tupleOfIdentifiers ( K_IN ( '(' ')' |tupleInMarker= inMarkerForTuple |literals= tupleOfTupleLiterals |markers= tupleOfMarkersForTuples ) |type= relationType literal= tupleLiteral |type= relationType tupleMarker= markerForTuple ) | '(' relation[$clauses] ')' );";
		}
	}

	static final String DFA194_eotS =
			"\12\uffff";
	static final String DFA194_eofS =
			"\12\uffff";
	static final String DFA194_minS =
			"\1\115\1\uffff\6\u00a8\2\uffff";
	static final String DFA194_maxS =
			"\1\u00c5\1\uffff\6\u00bf\2\uffff";
	static final String DFA194_acceptS =
			"\1\uffff\1\1\6\uffff\1\2\1\3";
	static final String DFA194_specialS =
			"\12\uffff}>";
	static final String[] DFA194_transitionS = {
			"\1\1\150\uffff\1\7\12\uffff\1\3\1\4\1\2\1\5\1\6",
			"",
			"\1\11\16\uffff\1\10\7\uffff\1\11",
			"\1\11\16\uffff\1\10\7\uffff\1\11",
			"\1\11\16\uffff\1\10\7\uffff\1\11",
			"\1\11\16\uffff\1\10\7\uffff\1\11",
			"\1\11\16\uffff\1\10\7\uffff\1\11",
			"\1\11\16\uffff\1\10\7\uffff\1\11",
			"",
			""
	};

	static final short[] DFA194_eot = DFA.unpackEncodedString(DFA194_eotS);
	static final short[] DFA194_eof = DFA.unpackEncodedString(DFA194_eofS);
	static final char[] DFA194_min = DFA.unpackEncodedStringToUnsignedChars(DFA194_minS);
	static final char[] DFA194_max = DFA.unpackEncodedStringToUnsignedChars(DFA194_maxS);
	static final short[] DFA194_accept = DFA.unpackEncodedString(DFA194_acceptS);
	static final short[] DFA194_special = DFA.unpackEncodedString(DFA194_specialS);
	static final short[][] DFA194_transition;

	static {
		int numStates = DFA194_transitionS.length;
		DFA194_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA194_transition[i] = DFA.unpackEncodedString(DFA194_transitionS[i]);
		}
	}

	protected class DFA194 extends DFA {

		public DFA194(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 194;
			this.eot = DFA194_eot;
			this.eof = DFA194_eof;
			this.min = DFA194_min;
			this.max = DFA194_max;
			this.accept = DFA194_accept;
			this.special = DFA194_special;
			this.transition = DFA194_transition;
		}
		@Override
		public String getDescription() {
			return "1473:7: ( K_IN ( '(' ')' |tupleInMarker= inMarkerForTuple |literals= tupleOfTupleLiterals |markers= tupleOfMarkersForTuples ) |type= relationType literal= tupleLiteral |type= relationType tupleMarker= markerForTuple )";
		}
	}

	static final String DFA204_eotS =
			"\40\uffff";
	static final String DFA204_eofS =
			"\1\uffff\25\35\2\32\1\uffff\1\32\1\uffff\1\32\4\uffff";
	static final String DFA204_minS =
			"\1\26\27\103\1\uffff\1\103\1\uffff\1\103\4\uffff";
	static final String DFA204_maxS =
			"\1\u00ac\27\u00c4\1\uffff\1\u00c4\1\uffff\1\u00c4\4\uffff";
	static final String DFA204_acceptS =
			"\30\uffff\1\2\1\uffff\1\4\1\uffff\1\6\1\1\1\3\1\5";
	static final String DFA204_specialS =
			"\40\uffff}>";
	static final String[] DFA204_transitionS = {
			"\1\32\4\uffff\2\32\4\uffff\1\32\1\uffff\1\1\3\uffff\1\2\1\3\1\4\1\uffff"+
					"\3\32\1\uffff\3\32\1\5\1\uffff\1\32\1\24\1\6\4\uffff\1\32\1\7\1\uffff"+
					"\1\10\2\uffff\3\32\1\11\1\uffff\1\33\1\uffff\2\32\1\uffff\1\32\3\uffff"+
					"\1\12\1\uffff\2\32\1\uffff\1\13\2\uffff\3\32\1\uffff\3\32\1\uffff\1\27"+
					"\1\32\1\26\5\uffff\1\32\1\uffff\1\32\4\uffff\1\32\2\uffff\5\32\3\uffff"+
					"\1\32\1\uffff\2\32\1\uffff\1\30\1\32\1\14\4\32\1\15\1\25\1\16\1\23\1"+
					"\17\2\uffff\1\32\1\uffff\1\32\1\31\1\32\4\uffff\2\32\1\uffff\1\20\1\32"+
					"\1\21\1\22\3\uffff\1\32\11\uffff\1\32\2\uffff\1\34",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\35\15\uffff\1\35\12\uffff\1\35\31\uffff\1\35\12\uffff\1\35\66\uffff"+
					"\1\35\2\uffff\1\35\2\uffff\1\32\1\uffff\1\35\3\uffff\1\35",
			"\1\32\15\uffff\1\32\12\uffff\1\32\31\uffff\1\32\12\uffff\1\32\66\uffff"+
					"\1\32\2\uffff\1\32\2\uffff\1\32\1\uffff\1\32\1\30\2\uffff\1\32",
			"\1\32\15\uffff\1\32\12\uffff\1\32\31\uffff\1\32\12\uffff\1\32\66\uffff"+
					"\1\32\2\uffff\1\32\2\uffff\1\32\1\uffff\1\32\1\30\2\uffff\1\32",
			"",
			"\1\32\15\uffff\1\32\12\uffff\1\32\31\uffff\1\32\12\uffff\1\32\66\uffff"+
					"\1\32\2\uffff\1\32\2\uffff\1\32\1\uffff\1\32\1\36\2\uffff\1\32",
			"",
			"\1\32\15\uffff\1\32\12\uffff\1\32\31\uffff\1\32\12\uffff\1\32\66\uffff"+
					"\1\32\2\uffff\1\32\2\uffff\1\32\1\uffff\1\32\1\37\2\uffff\1\32",
			"",
			"",
			"",
			""
	};

	static final short[] DFA204_eot = DFA.unpackEncodedString(DFA204_eotS);
	static final short[] DFA204_eof = DFA.unpackEncodedString(DFA204_eofS);
	static final char[] DFA204_min = DFA.unpackEncodedStringToUnsignedChars(DFA204_minS);
	static final char[] DFA204_max = DFA.unpackEncodedStringToUnsignedChars(DFA204_maxS);
	static final short[] DFA204_accept = DFA.unpackEncodedString(DFA204_acceptS);
	static final short[] DFA204_special = DFA.unpackEncodedString(DFA204_specialS);
	static final short[][] DFA204_transition;

	static {
		int numStates = DFA204_transitionS.length;
		DFA204_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA204_transition[i] = DFA.unpackEncodedString(DFA204_transitionS[i]);
		}
	}

	protected class DFA204 extends DFA {

		public DFA204(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 204;
			this.eot = DFA204_eot;
			this.eof = DFA204_eof;
			this.min = DFA204_min;
			this.max = DFA204_max;
			this.accept = DFA204_accept;
			this.special = DFA204_special;
			this.transition = DFA204_transition;
		}
		@Override
		public String getDescription() {
			return "1530:1: comparatorType returns [CQL3Type.Raw t] : (n= native_type |c= collection_type |tt= tuple_type |id= userTypeName | K_FROZEN '<' f= comparatorType '>' |s= STRING_LITERAL );";
		}
	}

	public static final BitSet FOLLOW_selectStatement_in_cqlStatement59 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insertStatement_in_cqlStatement88 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateStatement_in_cqlStatement117 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_batchStatement_in_cqlStatement146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deleteStatement_in_cqlStatement176 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_useStatement_in_cqlStatement205 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_truncateStatement_in_cqlStatement237 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createKeyspaceStatement_in_cqlStatement264 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createTableStatement_in_cqlStatement285 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createIndexStatement_in_cqlStatement308 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropKeyspaceStatement_in_cqlStatement331 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropTableStatement_in_cqlStatement353 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropIndexStatement_in_cqlStatement378 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterTableStatement_in_cqlStatement403 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterKeyspaceStatement_in_cqlStatement427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_grantPermissionsStatement_in_cqlStatement448 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_revokePermissionsStatement_in_cqlStatement466 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_listPermissionsStatement_in_cqlStatement483 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createUserStatement_in_cqlStatement502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterUserStatement_in_cqlStatement526 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropUserStatement_in_cqlStatement551 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_listUsersStatement_in_cqlStatement577 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createTriggerStatement_in_cqlStatement602 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropTriggerStatement_in_cqlStatement623 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createTypeStatement_in_cqlStatement646 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterTypeStatement_in_cqlStatement670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropTypeStatement_in_cqlStatement695 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createFunctionStatement_in_cqlStatement721 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropFunctionStatement_in_cqlStatement741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createAggregateStatement_in_cqlStatement763 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropAggregateStatement_in_cqlStatement782 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createRoleStatement_in_cqlStatement803 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterRoleStatement_in_cqlStatement827 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropRoleStatement_in_cqlStatement852 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_listRolesStatement_in_cqlStatement878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_grantRoleStatement_in_cqlStatement903 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_revokeRoleStatement_in_cqlStatement928 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createMaterializedViewStatement_in_cqlStatement952 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropMaterializedViewStatement_in_cqlStatement964 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterMaterializedViewStatement_in_cqlStatement978 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_USE_in_useStatement1004 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_keyspaceName_in_useStatement1008 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_SELECT_in_selectStatement1042 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x00000000000004C0L});
	public static final BitSet FOLLOW_K_JSON_in_selectStatement1052 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x00000000000004C0L});
	public static final BitSet FOLLOW_K_DISTINCT_in_selectStatement1069 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x00000000000004C0L});
	public static final BitSet FOLLOW_selectClause_in_selectStatement1078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_K_FROM_in_selectStatement1088 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_selectStatement1092 = new BitSet(new long[]{0x0000000020000002L,0x0009000040000800L,0x0000000020000000L});
	public static final BitSet FOLLOW_K_WHERE_in_selectStatement1102 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61DBFFL,0x0000000000000200L});
	public static final BitSet FOLLOW_whereClause_in_selectStatement1106 = new BitSet(new long[]{0x0000000020000002L,0x0009000040000800L});
	public static final BitSet FOLLOW_K_GROUP_in_selectStatement1119 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_K_BY_in_selectStatement1121 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_groupByClause_in_selectStatement1123 = new BitSet(new long[]{0x0000000020000002L,0x0009000040000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_selectStatement1128 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_groupByClause_in_selectStatement1130 = new BitSet(new long[]{0x0000000020000002L,0x0009000040000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_K_ORDER_in_selectStatement1147 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_K_BY_in_selectStatement1149 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_orderByClause_in_selectStatement1151 = new BitSet(new long[]{0x0000000020000002L,0x0008000040000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_selectStatement1156 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_orderByClause_in_selectStatement1158 = new BitSet(new long[]{0x0000000020000002L,0x0008000040000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_K_PER_in_selectStatement1175 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_K_PARTITION_in_selectStatement1177 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_LIMIT_in_selectStatement1179 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_intValue_in_selectStatement1183 = new BitSet(new long[]{0x0000000020000002L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_LIMIT_in_selectStatement1198 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_intValue_in_selectStatement1202 = new BitSet(new long[]{0x0000000020000002L});
	public static final BitSet FOLLOW_K_ALLOW_in_selectStatement1217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_K_FILTERING_in_selectStatement1219 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_selector_in_selectClause1256 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_selectClause1261 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_selector_in_selectClause1265 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_199_in_selectClause1277 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unaliasedSelector_in_selector1310 = new BitSet(new long[]{0x0000000200000002L});
	public static final BitSet FOLLOW_K_AS_in_selector1313 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_selector1317 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_unaliasedSelector1360 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_value_in_unaliasedSelector1408 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_183_in_unaliasedSelector1455 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_unaliasedSelector1459 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_unaliasedSelector1461 = new BitSet(new long[]{0x0000000000910840L,0x0000084000010000L,0x9080910000000000L,0x0000000000000440L});
	public static final BitSet FOLLOW_value_in_unaliasedSelector1465 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_K_COUNT_in_unaliasedSelector1486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_unaliasedSelector1488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_199_in_unaliasedSelector1490 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_unaliasedSelector1492 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_K_WRITETIME_in_unaliasedSelector1526 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_unaliasedSelector1528 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_unaliasedSelector1532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_unaliasedSelector1534 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_K_TTL_in_unaliasedSelector1560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_unaliasedSelector1568 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_unaliasedSelector1572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_unaliasedSelector1574 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_K_CAST_in_unaliasedSelector1600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_unaliasedSelector1607 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_unaliasedSelector_in_unaliasedSelector1611 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_K_AS_in_unaliasedSelector1613 = new BitSet(new long[]{0x5064038800000000L,0x0000000000108010L,0x000000000D0003E1L});
	public static final BitSet FOLLOW_native_type_in_unaliasedSelector1617 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_unaliasedSelector1619 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_functionName_in_unaliasedSelector1634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_selectionFunctionArgs_in_unaliasedSelector1638 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_190_in_unaliasedSelector1653 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_unaliasedSelector1657 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_183_in_selectionFunctionArgs1685 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_selectionFunctionArgs1687 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_selectionFunctionArgs1697 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_unaliasedSelector_in_selectionFunctionArgs1701 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_selectionFunctionArgs1717 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_unaliasedSelector_in_selectionFunctionArgs1721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_selectionFunctionArgs1734 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_relationOrExpression_in_whereClause1765 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_whereClause1769 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61DBFFL,0x0000000000000200L});
	public static final BitSet FOLLOW_relationOrExpression_in_whereClause1771 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_relation_in_relationOrExpression1793 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_customIndexExpression_in_relationOrExpression1802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_201_in_customIndexExpression1830 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_idxName_in_customIndexExpression1832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_customIndexExpression1835 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_customIndexExpression1839 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_customIndexExpression1841 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_orderByClause1871 = new BitSet(new long[]{0x0200000400000002L});
	public static final BitSet FOLLOW_K_ASC_in_orderByClause1874 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DESC_in_orderByClause1878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_groupByClause1904 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_INSERT_in_insertStatement1929 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_K_INTO_in_insertStatement1931 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_insertStatement1935 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L,0x0080000000000000L});
	public static final BitSet FOLLOW_normalInsertStatement_in_insertStatement1949 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_JSON_in_insertStatement1964 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000110000000000L});
	public static final BitSet FOLLOW_jsonInsertStatement_in_insertStatement1968 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_normalInsertStatement2004 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_normalInsertStatement2008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_normalInsertStatement2015 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_normalInsertStatement2019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_normalInsertStatement2026 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_K_VALUES_in_normalInsertStatement2034 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_normalInsertStatement2042 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_normalInsertStatement2046 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_normalInsertStatement2052 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_normalInsertStatement2056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_normalInsertStatement2063 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L,0x0000000000800000L});
	public static final BitSet FOLLOW_K_IF_in_normalInsertStatement2073 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_normalInsertStatement2075 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_normalInsertStatement2077 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_usingClause_in_normalInsertStatement2092 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_jsonValue_in_jsonInsertStatement2138 = new BitSet(new long[]{0x0080000000000002L,0x0000000000001000L,0x0000000000800000L});
	public static final BitSet FOLLOW_K_DEFAULT_in_jsonInsertStatement2148 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_K_NULL_in_jsonInsertStatement2152 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L,0x0000000000800000L});
	public static final BitSet FOLLOW_K_UNSET_in_jsonInsertStatement2160 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L,0x0000000000800000L});
	public static final BitSet FOLLOW_K_IF_in_jsonInsertStatement2176 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_jsonInsertStatement2178 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_jsonInsertStatement2180 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_usingClause_in_jsonInsertStatement2195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_jsonValue2230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_191_in_jsonValue2240 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_jsonValue2244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_jsonValue2258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_USING_in_usingClause2289 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004080L});
	public static final BitSet FOLLOW_usingClauseObjective_in_usingClause2291 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_usingClause2296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004080L});
	public static final BitSet FOLLOW_usingClauseObjective_in_usingClause2298 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_TIMESTAMP_in_usingClauseObjective2320 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_intValue_in_usingClauseObjective2324 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TTL_in_usingClauseObjective2334 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_intValue_in_usingClauseObjective2338 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_UPDATE_in_updateStatement2372 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_updateStatement2376 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_usingClause_in_updateStatement2386 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_K_SET_in_updateStatement2398 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_columnOperation_in_updateStatement2400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000020000000L});
	public static final BitSet FOLLOW_187_in_updateStatement2404 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_columnOperation_in_updateStatement2406 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000020000000L});
	public static final BitSet FOLLOW_K_WHERE_in_updateStatement2417 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61DBFFL,0x0000000000000200L});
	public static final BitSet FOLLOW_whereClause_in_updateStatement2421 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
	public static final BitSet FOLLOW_K_IF_in_updateStatement2431 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_K_EXISTS_in_updateStatement2435 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateConditions_in_updateStatement2443 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_columnCondition_in_updateConditions2485 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_updateConditions2490 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_columnCondition_in_updateConditions2492 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_DELETE_in_deleteStatement2529 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B7EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_deleteSelection_in_deleteStatement2535 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_K_FROM_in_deleteStatement2548 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_deleteStatement2552 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000020800000L});
	public static final BitSet FOLLOW_usingClauseDelete_in_deleteStatement2562 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_K_WHERE_in_deleteStatement2574 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61DBFFL,0x0000000000000200L});
	public static final BitSet FOLLOW_whereClause_in_deleteStatement2578 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
	public static final BitSet FOLLOW_K_IF_in_deleteStatement2588 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_K_EXISTS_in_deleteStatement2592 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateConditions_in_deleteStatement2600 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deleteOp_in_deleteSelection2647 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_deleteSelection2662 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_deleteOp_in_deleteSelection2666 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_cident_in_deleteOp2693 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_deleteOp2720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_198_in_deleteOp2722 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_deleteOp2726 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_200_in_deleteOp2728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_deleteOp2740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_190_in_deleteOp2742 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_deleteOp2746 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_USING_in_usingClauseDelete2766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_K_TIMESTAMP_in_usingClauseDelete2768 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_intValue_in_usingClauseDelete2772 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_BEGIN_in_batchStatement2806 = new BitSet(new long[]{0x0004002000000000L,0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_K_UNLOGGED_in_batchStatement2816 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_K_COUNTER_in_batchStatement2822 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_K_BATCH_in_batchStatement2835 = new BitSet(new long[]{0x0100000100000000L,0x0000000000080000L,0x0000000000880000L});
	public static final BitSet FOLLOW_usingClause_in_batchStatement2839 = new BitSet(new long[]{0x0100000100000000L,0x0000000000080000L,0x0000000000080000L});
	public static final BitSet FOLLOW_batchStatementObjective_in_batchStatement2859 = new BitSet(new long[]{0x0100000100000000L,0x0000000000080000L,0x0000000000080000L,0x0000000000000001L});
	public static final BitSet FOLLOW_192_in_batchStatement2861 = new BitSet(new long[]{0x0100000100000000L,0x0000000000080000L,0x0000000000080000L});
	public static final BitSet FOLLOW_K_APPLY_in_batchStatement2875 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_K_BATCH_in_batchStatement2877 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insertStatement_in_batchStatementObjective2908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_updateStatement_in_batchStatementObjective2921 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_deleteStatement_in_batchStatementObjective2934 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createAggregateStatement2967 = new BitSet(new long[]{0x0000000008000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_K_OR_in_createAggregateStatement2970 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_K_REPLACE_in_createAggregateStatement2972 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_K_AGGREGATE_in_createAggregateStatement2984 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_K_IF_in_createAggregateStatement2993 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createAggregateStatement2995 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createAggregateStatement2997 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_functionName_in_createAggregateStatement3011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_createAggregateStatement3019 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x010012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_createAggregateStatement3043 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_createAggregateStatement3059 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_createAggregateStatement3063 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_createAggregateStatement3087 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_K_SFUNC_in_createAggregateStatement3095 = new BitSet(new long[]{0x5077AB8A18400000L,0x9A3E4283BA168B5EL,0x000002000F619BFFL});
	public static final BitSet FOLLOW_allowedFunctionName_in_createAggregateStatement3101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_K_STYPE_in_createAggregateStatement3109 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_createAggregateStatement3115 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020008L});
	public static final BitSet FOLLOW_K_FINALFUNC_in_createAggregateStatement3133 = new BitSet(new long[]{0x5077AB8A18400000L,0x9A3E4283BA168B5EL,0x000002000F619BFFL});
	public static final BitSet FOLLOW_allowedFunctionName_in_createAggregateStatement3139 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
	public static final BitSet FOLLOW_K_INITCOND_in_createAggregateStatement3166 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_createAggregateStatement3172 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropAggregateStatement3219 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_K_AGGREGATE_in_dropAggregateStatement3221 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_K_IF_in_dropAggregateStatement3230 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropAggregateStatement3232 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_functionName_in_dropAggregateStatement3247 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_dropAggregateStatement3265 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x010012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_dropAggregateStatement3293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_dropAggregateStatement3311 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_dropAggregateStatement3315 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_dropAggregateStatement3343 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createFunctionStatement3400 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000100L});
	public static final BitSet FOLLOW_K_OR_in_createFunctionStatement3403 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_K_REPLACE_in_createFunctionStatement3405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_K_FUNCTION_in_createFunctionStatement3417 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_K_IF_in_createFunctionStatement3426 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createFunctionStatement3428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createFunctionStatement3430 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_functionName_in_createFunctionStatement3444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_createFunctionStatement3452 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x010002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_createFunctionStatement3476 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_createFunctionStatement3480 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_createFunctionStatement3496 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_createFunctionStatement3500 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_createFunctionStatement3504 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_createFunctionStatement3528 = new BitSet(new long[]{0x0000080000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_K_RETURNS_in_createFunctionStatement3539 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_K_NULL_in_createFunctionStatement3541 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_K_CALLED_in_createFunctionStatement3547 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_K_ON_in_createFunctionStatement3553 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_K_NULL_in_createFunctionStatement3555 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_K_INPUT_in_createFunctionStatement3557 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_K_RETURNS_in_createFunctionStatement3565 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_createFunctionStatement3571 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_K_LANGUAGE_in_createFunctionStatement3579 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_IDENT_in_createFunctionStatement3585 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_K_AS_in_createFunctionStatement3593 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_createFunctionStatement3599 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropFunctionStatement3637 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_K_FUNCTION_in_dropFunctionStatement3639 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_K_IF_in_dropFunctionStatement3648 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropFunctionStatement3650 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_functionName_in_dropFunctionStatement3665 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_dropFunctionStatement3683 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x010012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_dropFunctionStatement3711 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_dropFunctionStatement3729 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_dropFunctionStatement3733 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_dropFunctionStatement3761 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createKeyspaceStatement3820 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_K_KEYSPACE_in_createKeyspaceStatement3822 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_createKeyspaceStatement3825 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createKeyspaceStatement3827 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createKeyspaceStatement3829 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_keyspaceName_in_createKeyspaceStatement3838 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_createKeyspaceStatement3846 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_properties_in_createKeyspaceStatement3848 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createTableStatement3883 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_K_COLUMNFAMILY_in_createTableStatement3885 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_createTableStatement3888 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createTableStatement3890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createTableStatement3892 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_createTableStatement3907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_cfamDefinition_in_createTableStatement3917 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_cfamDefinition3936 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A7E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cfamColumns_in_cfamDefinition3938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_cfamDefinition3943 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A7E4283BB968B5EL,0x090002008F61D3FFL});
	public static final BitSet FOLLOW_cfamColumns_in_cfamDefinition3945 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_cfamDefinition3952 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_cfamDefinition3962 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cfamProperty_in_cfamDefinition3964 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_cfamDefinition3969 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cfamProperty_in_cfamDefinition3971 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_ident_in_cfamColumns3997 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_cfamColumns4001 = new BitSet(new long[]{0x0000000000000002L,0x0040000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_STATIC_in_cfamColumns4006 = new BitSet(new long[]{0x0000000000000002L,0x0040000000000000L});
	public static final BitSet FOLLOW_K_PRIMARY_in_cfamColumns4023 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_K_KEY_in_cfamColumns4025 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_PRIMARY_in_cfamColumns4037 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_K_KEY_in_cfamColumns4039 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_cfamColumns4041 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61D3FFL});
	public static final BitSet FOLLOW_pkDef_in_cfamColumns4043 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_cfamColumns4047 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_ident_in_cfamColumns4051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_cfamColumns4058 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ident_in_pkDef4078 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_pkDef4088 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_ident_in_pkDef4094 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_pkDef4100 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_ident_in_pkDef4104 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_pkDef4111 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_property_in_cfamProperty4131 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_COMPACT_in_cfamProperty4140 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_K_STORAGE_in_cfamProperty4142 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CLUSTERING_in_cfamProperty4152 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
	public static final BitSet FOLLOW_K_ORDER_in_cfamProperty4154 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_K_BY_in_cfamProperty4156 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_cfamProperty4158 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cfamOrdering_in_cfamProperty4160 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_cfamProperty4164 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cfamOrdering_in_cfamProperty4166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_cfamProperty4171 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ident_in_cfamOrdering4199 = new BitSet(new long[]{0x0200000400000000L});
	public static final BitSet FOLLOW_K_ASC_in_cfamOrdering4202 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DESC_in_cfamOrdering4206 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createTypeStatement4245 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_K_TYPE_in_createTypeStatement4247 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_createTypeStatement4250 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createTypeStatement4252 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createTypeStatement4254 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_userTypeName_in_createTypeStatement4272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_createTypeStatement4285 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_typeColumns_in_createTypeStatement4287 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_createTypeStatement4292 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x090002008F61D3FFL});
	public static final BitSet FOLLOW_typeColumns_in_createTypeStatement4294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_createTypeStatement4301 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fident_in_typeColumns4321 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_typeColumns4325 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createIndexStatement4360 = new BitSet(new long[]{0x0010000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_K_CUSTOM_in_createIndexStatement4363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_K_INDEX_in_createIndexStatement4369 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E6283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_createIndexStatement4372 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createIndexStatement4374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createIndexStatement4376 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E6283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_idxName_in_createIndexStatement4392 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_K_ON_in_createIndexStatement4397 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_createIndexStatement4401 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_createIndexStatement4403 = new BitSet(new long[]{0xD877BB8A18400000L,0x9A3E4283BB968BDEL,0x010002008F61D3FFL});
	public static final BitSet FOLLOW_indexIdent_in_createIndexStatement4406 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_createIndexStatement4410 = new BitSet(new long[]{0xD877BB8A18400000L,0x9A3E4283BB968BDEL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_indexIdent_in_createIndexStatement4412 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_createIndexStatement4419 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000040800000L});
	public static final BitSet FOLLOW_K_USING_in_createIndexStatement4430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_createIndexStatement4434 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_createIndexStatement4449 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_properties_in_createIndexStatement4451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_indexIdent4483 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_VALUES_in_indexIdent4511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_indexIdent4513 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_indexIdent4517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_indexIdent4519 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_KEYS_in_indexIdent4530 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_indexIdent4532 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_indexIdent4536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_indexIdent4538 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ENTRIES_in_indexIdent4551 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_indexIdent4553 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_indexIdent4557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_indexIdent4559 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_FULL_in_indexIdent4569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_indexIdent4571 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_indexIdent4575 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_indexIdent4577 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createMaterializedViewStatement4614 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_K_MATERIALIZED_in_createMaterializedViewStatement4616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_K_VIEW_in_createMaterializedViewStatement4618 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_createMaterializedViewStatement4621 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createMaterializedViewStatement4623 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createMaterializedViewStatement4625 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_createMaterializedViewStatement4633 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_K_AS_in_createMaterializedViewStatement4635 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_K_SELECT_in_createMaterializedViewStatement4645 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x00000000000004C0L});
	public static final BitSet FOLLOW_selectClause_in_createMaterializedViewStatement4649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_K_FROM_in_createMaterializedViewStatement4651 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_createMaterializedViewStatement4655 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_K_WHERE_in_createMaterializedViewStatement4666 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61DBFFL,0x0000000000000200L});
	public static final BitSet FOLLOW_whereClause_in_createMaterializedViewStatement4670 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
	public static final BitSet FOLLOW_K_PRIMARY_in_createMaterializedViewStatement4682 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_K_KEY_in_createMaterializedViewStatement4684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_createMaterializedViewStatement4696 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_createMaterializedViewStatement4698 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_createMaterializedViewStatement4702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_createMaterializedViewStatement4708 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_createMaterializedViewStatement4712 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_createMaterializedViewStatement4719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_createMaterializedViewStatement4723 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_createMaterializedViewStatement4727 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_createMaterializedViewStatement4734 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_183_in_createMaterializedViewStatement4744 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_createMaterializedViewStatement4748 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_createMaterializedViewStatement4754 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_createMaterializedViewStatement4758 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_createMaterializedViewStatement4765 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_createMaterializedViewStatement4797 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cfamProperty_in_createMaterializedViewStatement4799 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_createMaterializedViewStatement4804 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cfamProperty_in_createMaterializedViewStatement4806 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createTriggerStatement4844 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_K_TRIGGER_in_createTriggerStatement4846 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_createTriggerStatement4849 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createTriggerStatement4851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createTriggerStatement4853 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_createTriggerStatement4863 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_K_ON_in_createTriggerStatement4874 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_createTriggerStatement4878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_K_USING_in_createTriggerStatement4880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_createTriggerStatement4884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropTriggerStatement4925 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_K_TRIGGER_in_dropTriggerStatement4927 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_dropTriggerStatement4930 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropTriggerStatement4932 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_dropTriggerStatement4942 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_K_ON_in_dropTriggerStatement4945 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_dropTriggerStatement4949 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALTER_in_alterKeyspaceStatement4989 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_K_KEYSPACE_in_alterKeyspaceStatement4991 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_keyspaceName_in_alterKeyspaceStatement4995 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_alterKeyspaceStatement5005 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_properties_in_alterKeyspaceStatement5007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALTER_in_alterTableStatement5042 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_K_COLUMNFAMILY_in_alterTableStatement5044 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_alterTableStatement5048 = new BitSet(new long[]{0x2000000044000000L,0x0080000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_ALTER_in_alterTableStatement5062 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_K_TYPE_in_alterTableStatement5069 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_alterTableStatement5073 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ADD_in_alterTableStatement5092 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5107 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_alterTableStatement5113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_cfisStatic_in_alterTableStatement5119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_alterTableStatement5148 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5153 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_alterTableStatement5158 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000002L});
	public static final BitSet FOLLOW_cfisStatic_in_alterTableStatement5163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_alterTableStatement5192 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5196 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_alterTableStatement5201 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000002L});
	public static final BitSet FOLLOW_cfisStatic_in_alterTableStatement5206 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_alterTableStatement5213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_alterTableStatement5233 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5249 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_183_in_alterTableStatement5279 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_alterTableStatement5314 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5318 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_alterTableStatement5325 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_K_USING_in_alterTableStatement5353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000080L});
	public static final BitSet FOLLOW_K_TIMESTAMP_in_alterTableStatement5355 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_INTEGER_in_alterTableStatement5359 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_WITH_in_alterTableStatement5381 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_properties_in_alterTableStatement5384 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_RENAME_in_alterTableStatement5417 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_K_TO_in_alterTableStatement5473 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5477 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_alterTableStatement5498 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_K_TO_in_alterTableStatement5504 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_alterTableStatement5508 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_STATIC_in_cfisStatic5561 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALTER_in_alterMaterializedViewStatement5597 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_K_MATERIALIZED_in_alterMaterializedViewStatement5599 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_K_VIEW_in_alterMaterializedViewStatement5601 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_alterMaterializedViewStatement5605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_alterMaterializedViewStatement5617 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_properties_in_alterMaterializedViewStatement5619 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALTER_in_alterTypeStatement5650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_K_TYPE_in_alterTypeStatement5652 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_userTypeName_in_alterTypeStatement5656 = new BitSet(new long[]{0x0000000044000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_K_ALTER_in_alterTypeStatement5670 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_alterTypeStatement5674 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_K_TYPE_in_alterTypeStatement5676 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_alterTypeStatement5680 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ADD_in_alterTypeStatement5696 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_alterTypeStatement5702 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_alterTypeStatement5706 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_RENAME_in_alterTypeStatement5729 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_alterTypeStatement5767 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_K_TO_in_alterTypeStatement5769 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_alterTypeStatement5773 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_alterTypeStatement5796 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_alterTypeStatement5800 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_K_TO_in_alterTypeStatement5802 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_alterTypeStatement5806 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropKeyspaceStatement5873 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_K_KEYSPACE_in_dropKeyspaceStatement5875 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_dropKeyspaceStatement5878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropKeyspaceStatement5880 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_keyspaceName_in_dropKeyspaceStatement5889 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropTableStatement5923 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_K_COLUMNFAMILY_in_dropTableStatement5925 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_dropTableStatement5928 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropTableStatement5930 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_dropTableStatement5939 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropTypeStatement5973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_K_TYPE_in_dropTypeStatement5975 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_dropTypeStatement5978 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropTypeStatement5980 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_userTypeName_in_dropTypeStatement5989 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropIndexStatement6023 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_K_INDEX_in_dropIndexStatement6025 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_dropIndexStatement6028 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropIndexStatement6030 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_indexName_in_dropIndexStatement6039 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropMaterializedViewStatement6079 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_K_MATERIALIZED_in_dropMaterializedViewStatement6081 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_K_VIEW_in_dropMaterializedViewStatement6083 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_dropMaterializedViewStatement6086 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropMaterializedViewStatement6088 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_dropMaterializedViewStatement6097 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TRUNCATE_in_truncateStatement6128 = new BitSet(new long[]{0x5877FB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_K_COLUMNFAMILY_in_truncateStatement6131 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_truncateStatement6137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_GRANT_in_grantPermissionsStatement6162 = new BitSet(new long[]{0x2408001050000000L,0x2000002000000001L});
	public static final BitSet FOLLOW_permissionOrAll_in_grantPermissionsStatement6174 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_K_ON_in_grantPermissionsStatement6182 = new BitSet(new long[]{0x5877FB8A18400000L,0x9A3E429BBF968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_resource_in_grantPermissionsStatement6194 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_K_TO_in_grantPermissionsStatement6202 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_grantPermissionsStatement6216 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_REVOKE_in_revokePermissionsStatement6247 = new BitSet(new long[]{0x2408001050000000L,0x2000002000000001L});
	public static final BitSet FOLLOW_permissionOrAll_in_revokePermissionsStatement6259 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_K_ON_in_revokePermissionsStatement6267 = new BitSet(new long[]{0x5877FB8A18400000L,0x9A3E429BBF968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_resource_in_revokePermissionsStatement6279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_K_FROM_in_revokePermissionsStatement6287 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_revokePermissionsStatement6301 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_GRANT_in_grantRoleStatement6332 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_grantRoleStatement6346 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_K_TO_in_grantRoleStatement6354 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_grantRoleStatement6368 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_REVOKE_in_revokeRoleStatement6399 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_revokeRoleStatement6413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_K_FROM_in_revokeRoleStatement6421 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_revokeRoleStatement6435 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_LIST_in_listPermissionsStatement6473 = new BitSet(new long[]{0x2408001050000000L,0x2000002000000001L});
	public static final BitSet FOLLOW_permissionOrAll_in_listPermissionsStatement6485 = new BitSet(new long[]{0x0000000000000002L,0x0000310000000000L});
	public static final BitSet FOLLOW_K_ON_in_listPermissionsStatement6495 = new BitSet(new long[]{0x5877FB8A18400000L,0x9A3E429BBF968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_resource_in_listPermissionsStatement6497 = new BitSet(new long[]{0x0000000000000002L,0x0000110000000000L});
	public static final BitSet FOLLOW_K_OF_in_listPermissionsStatement6512 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_roleName_in_listPermissionsStatement6514 = new BitSet(new long[]{0x0000000000000002L,0x0000010000000000L});
	public static final BitSet FOLLOW_K_NORECURSIVE_in_listPermissionsStatement6528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_permission6564 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALL_in_permissionOrAll6621 = new BitSet(new long[]{0x0000000000000002L,0x0020000000000000L});
	public static final BitSet FOLLOW_K_PERMISSIONS_in_permissionOrAll6625 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_permission_in_permissionOrAll6646 = new BitSet(new long[]{0x0000000000000002L,0x0010000000000000L});
	public static final BitSet FOLLOW_K_PERMISSION_in_permissionOrAll6650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dataResource_in_resource6678 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_roleResource_in_resource6690 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_functionResource_in_resource6702 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_jmxResource_in_resource6714 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALL_in_dataResource6737 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_K_KEYSPACES_in_dataResource6739 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_KEYSPACE_in_dataResource6749 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_keyspaceName_in_dataResource6755 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_COLUMNFAMILY_in_dataResource6767 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_columnFamilyName_in_dataResource6776 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALL_in_jmxResource6805 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_K_MBEANS_in_jmxResource6807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_MBEAN_in_jmxResource6827 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_mbean_in_jmxResource6829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_MBEANS_in_jmxResource6839 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_mbean_in_jmxResource6841 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALL_in_roleResource6864 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
	public static final BitSet FOLLOW_K_ROLES_in_roleResource6866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ROLE_in_roleResource6876 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_roleResource6882 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALL_in_functionResource6914 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_K_FUNCTIONS_in_functionResource6916 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALL_in_functionResource6926 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_K_FUNCTIONS_in_functionResource6928 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_K_IN_in_functionResource6930 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_K_KEYSPACE_in_functionResource6932 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_keyspaceName_in_functionResource6938 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_FUNCTION_in_functionResource6953 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61DBFFL});
	public static final BitSet FOLLOW_functionName_in_functionResource6957 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_functionResource6975 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x010012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_functionResource7003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_functionResource7021 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_functionResource7025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_functionResource7053 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createUserStatement7101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_K_USER_in_createUserStatement7103 = new BitSet(new long[]{0x0000000000400000L,0x0000000000001000L,0x0000120000000000L});
	public static final BitSet FOLLOW_K_IF_in_createUserStatement7106 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createUserStatement7108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createUserStatement7110 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000000L,0x0000120000000000L});
	public static final BitSet FOLLOW_username_in_createUserStatement7118 = new BitSet(new long[]{0x0000000000000002L,0x0000020000000000L,0x0000000040000010L});
	public static final BitSet FOLLOW_K_WITH_in_createUserStatement7130 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_userPassword_in_createUserStatement7132 = new BitSet(new long[]{0x0000000000000002L,0x0000020000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_K_SUPERUSER_in_createUserStatement7146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_NOSUPERUSER_in_createUserStatement7152 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALTER_in_alterUserStatement7197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_K_USER_in_alterUserStatement7199 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000000L,0x0000120000000000L});
	public static final BitSet FOLLOW_username_in_alterUserStatement7203 = new BitSet(new long[]{0x0000000000000002L,0x0000020000000000L,0x0000000040000010L});
	public static final BitSet FOLLOW_K_WITH_in_alterUserStatement7215 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
	public static final BitSet FOLLOW_userPassword_in_alterUserStatement7217 = new BitSet(new long[]{0x0000000000000002L,0x0000020000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_K_SUPERUSER_in_alterUserStatement7231 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_NOSUPERUSER_in_alterUserStatement7245 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropUserStatement7291 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_K_USER_in_dropUserStatement7293 = new BitSet(new long[]{0x0000000000400000L,0x0000000000001000L,0x0000120000000000L});
	public static final BitSet FOLLOW_K_IF_in_dropUserStatement7296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropUserStatement7298 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000000L,0x0000120000000000L});
	public static final BitSet FOLLOW_username_in_dropUserStatement7306 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_LIST_in_listUsersStatement7331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_K_USERS_in_listUsersStatement7333 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_CREATE_in_createRoleStatement7367 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_K_ROLE_in_createRoleStatement7369 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_createRoleStatement7372 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_createRoleStatement7374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_createRoleStatement7376 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_createRoleStatement7384 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_createRoleStatement7394 = new BitSet(new long[]{0x0000000000000000L,0x0004400100000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_roleOptions_in_createRoleStatement7396 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ALTER_in_alterRoleStatement7440 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_K_ROLE_in_alterRoleStatement7442 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_alterRoleStatement7446 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_K_WITH_in_alterRoleStatement7456 = new BitSet(new long[]{0x0000000000000000L,0x0004400100000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_roleOptions_in_alterRoleStatement7458 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DROP_in_dropRoleStatement7502 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_K_ROLE_in_dropRoleStatement7504 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB969B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_K_IF_in_dropRoleStatement7507 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_K_EXISTS_in_dropRoleStatement7509 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_userOrRoleName_in_dropRoleStatement7517 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_LIST_in_listRolesStatement7557 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
	public static final BitSet FOLLOW_K_ROLES_in_listRolesStatement7559 = new BitSet(new long[]{0x0000000000000002L,0x0000110000000000L});
	public static final BitSet FOLLOW_K_OF_in_listRolesStatement7569 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000013008F61D3FFL});
	public static final BitSet FOLLOW_roleName_in_listRolesStatement7571 = new BitSet(new long[]{0x0000000000000002L,0x0000010000000000L});
	public static final BitSet FOLLOW_K_NORECURSIVE_in_listRolesStatement7584 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_roleOption_in_roleOptions7615 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_roleOptions7619 = new BitSet(new long[]{0x0000000000000000L,0x0004400100000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_roleOption_in_roleOptions7621 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_PASSWORD_in_roleOption7643 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_195_in_roleOption7645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_roleOption7649 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_OPTIONS_in_roleOption7660 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_195_in_roleOption7662 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_mapLiteral_in_roleOption7666 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_SUPERUSER_in_roleOption7677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_195_in_roleOption7679 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_BOOLEAN_in_roleOption7683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_LOGIN_in_roleOption7694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_195_in_roleOption7696 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_BOOLEAN_in_roleOption7700 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_PASSWORD_in_userPassword7722 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_userPassword7726 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_cident7757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_cident7782 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_cident7801 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_ident7827 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_ident7852 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_ident7871 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_fident7896 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_fident7921 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_fident7940 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_noncol_ident7966 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_noncol_ident7991 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_noncol_ident8010 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ksName_in_keyspaceName8043 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ksName_in_indexName8077 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_190_in_indexName8080 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_idxName_in_indexName8084 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ksName_in_columnFamilyName8116 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_190_in_columnFamilyName8119 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000003008F61D3FFL});
	public static final BitSet FOLLOW_cfName_in_columnFamilyName8123 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_noncol_ident_in_userTypeName8148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_190_in_userTypeName8150 = new BitSet(new long[]{0x0011A80218400000L,0x9A3E4283BB060B4EL,0x000002000261901EL});
	public static final BitSet FOLLOW_non_type_ident_in_userTypeName8156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_roleName_in_userOrRoleName8188 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_ksName8211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_ksName8236 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_ksName8255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_ksName8265 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_cfName8287 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_cfName8312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_cfName8331 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_cfName8341 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_idxName8363 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_idxName8388 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_idxName8407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_idxName8417 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_roleName8439 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_roleName8464 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_roleName8480 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_roleName8499 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_roleName8509 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_constant8534 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_constant8546 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_in_constant8565 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOLEAN_in_constant8586 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DURATION_in_constant8605 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UUID_in_constant8623 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HEXNUMBER_in_constant8645 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_188_in_constant8663 = new BitSet(new long[]{0x0000000000000000L,0x0000004000010000L});
	public static final BitSet FOLLOW_set_in_constant8672 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_202_in_mapLiteral8701 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000C40L});
	public static final BitSet FOLLOW_term_in_mapLiteral8719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_191_in_mapLiteral8721 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_mapLiteral8725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_187_in_mapLiteral8731 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_mapLiteral8735 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_191_in_mapLiteral8737 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_mapLiteral8741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_203_in_mapLiteral8757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_191_in_setOrMapLiteral8781 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_setOrMapLiteral8785 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_setOrMapLiteral8801 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_setOrMapLiteral8805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_191_in_setOrMapLiteral8807 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_setOrMapLiteral8811 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_setOrMapLiteral8846 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_setOrMapLiteral8850 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_198_in_collectionLiteral8884 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000540L});
	public static final BitSet FOLLOW_term_in_collectionLiteral8902 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_187_in_collectionLiteral8908 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_collectionLiteral8912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_200_in_collectionLiteral8928 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_202_in_collectionLiteral8938 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_collectionLiteral8942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8800000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_setOrMapLiteral_in_collectionLiteral8946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_203_in_collectionLiteral8951 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_202_in_collectionLiteral8969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_203_in_collectionLiteral8971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_202_in_usertypeLiteral9015 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_usertypeLiteral9019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_191_in_usertypeLiteral9021 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_usertypeLiteral9025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_187_in_usertypeLiteral9031 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_usertypeLiteral9035 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_191_in_usertypeLiteral9037 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_usertypeLiteral9041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_203_in_usertypeLiteral9048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_tupleLiteral9085 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_tupleLiteral9089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_tupleLiteral9095 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_tupleLiteral9099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_tupleLiteral9106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constant_in_value9129 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_collectionLiteral_in_value9151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_usertypeLiteral_in_value9164 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tupleLiteral_in_value9179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_NULL_in_value9195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_191_in_value9219 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_value9223 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_value9234 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_in_intValue9274 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_191_in_intValue9288 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_intValue9292 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_intValue9303 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_keyspaceName_in_functionName9337 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x4000000000000000L});
	public static final BitSet FOLLOW_190_in_functionName9339 = new BitSet(new long[]{0x5077AB8A18400000L,0x9A3E4283BA168B5EL,0x000002000F619BFFL});
	public static final BitSet FOLLOW_allowedFunctionName_in_functionName9345 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_allowedFunctionName9372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_allowedFunctionName9406 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_function_keyword_in_allowedFunctionName9434 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TOKEN_in_allowedFunctionName9444 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_COUNT_in_allowedFunctionName9476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_functionName_in_function9523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_function9525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_function9527 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_functionName_in_function9557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_183_in_function9559 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_functionArgs_in_function9563 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_function9565 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_functionArgs9598 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_functionArgs9604 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_functionArgs9608 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_value_in_term9636 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_function_in_term9673 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_term9705 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_term9709 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_term9711 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_term9715 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_columnOperation9738 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x6400000000000000L,0x0000000000000048L});
	public static final BitSet FOLLOW_columnOperationDifferentiator_in_columnOperation9740 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_195_in_columnOperationDifferentiator9759 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_normalColumnOperation_in_columnOperationDifferentiator9761 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_shorthandColumnOperation_in_columnOperationDifferentiator9770 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_198_in_columnOperationDifferentiator9779 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_columnOperationDifferentiator9783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_200_in_columnOperationDifferentiator9785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_collectionColumnOperation_in_columnOperationDifferentiator9787 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_190_in_columnOperationDifferentiator9796 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_columnOperationDifferentiator9800 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_udtColumnOperation_in_columnOperationDifferentiator9802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_normalColumnOperation9823 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_185_in_normalColumnOperation9826 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_normalColumnOperation9830 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_normalColumnOperation9851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x1200000000000000L});
	public static final BitSet FOLLOW_set_in_normalColumnOperation9855 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_normalColumnOperation9865 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_normalColumnOperation9883 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_INTEGER_in_normalColumnOperation9887 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_shorthandColumnOperation9915 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_shorthandColumnOperation9925 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_195_in_collectionColumnOperation9951 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_collectionColumnOperation9955 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_195_in_udtColumnOperation9981 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_udtColumnOperation9985 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_columnCondition10018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L,0x4040000000000000L,0x000000000000007EL});
	public static final BitSet FOLLOW_relationType_in_columnCondition10032 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_columnCondition10036 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_IN_in_columnCondition10050 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8080010000000000L});
	public static final BitSet FOLLOW_singleColumnInValues_in_columnCondition10068 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inMarker_in_columnCondition10088 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_198_in_columnCondition10116 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_columnCondition10120 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_200_in_columnCondition10122 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L,0x0040000000000000L,0x000000000000003EL});
	public static final BitSet FOLLOW_relationType_in_columnCondition10140 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_columnCondition10144 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_IN_in_columnCondition10162 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8080010000000000L});
	public static final BitSet FOLLOW_singleColumnInValues_in_columnCondition10184 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inMarker_in_columnCondition10208 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_190_in_columnCondition10254 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_fident_in_columnCondition10258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L,0x0040000000000000L,0x000000000000003EL});
	public static final BitSet FOLLOW_relationType_in_columnCondition10276 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_columnCondition10280 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_IN_in_columnCondition10298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8080010000000000L});
	public static final BitSet FOLLOW_singleColumnInValues_in_columnCondition10320 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inMarker_in_columnCondition10344 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_property_in_properties10406 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_K_AND_in_properties10410 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_property_in_properties10412 = new BitSet(new long[]{0x0000000080000002L});
	public static final BitSet FOLLOW_noncol_ident_in_property10435 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_195_in_property10437 = new BitSet(new long[]{0x5877BB8A18910840L,0x9A3E42C3BB978B5EL,0x100090008F61D3FFL});
	public static final BitSet FOLLOW_propertyValue_in_property10441 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_noncol_ident_in_property10453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_195_in_property10455 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_mapLiteral_in_property10459 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constant_in_propertyValue10484 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_keyword_in_propertyValue10506 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_195_in_relationType10529 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_193_in_relationType10540 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_194_in_relationType10551 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_196_in_relationType10561 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_197_in_relationType10572 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_182_in_relationType10582 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_relation10604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0040000000000000L,0x000000000000003EL});
	public static final BitSet FOLLOW_relationType_in_relation10608 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_relation10612 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_relation10624 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_K_LIKE_in_relation10626 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_relation10630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_relation10642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_K_IS_in_relation10644 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_K_NOT_in_relation10646 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_K_NULL_in_relation10648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TOKEN_in_relation10658 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_tupleOfIdentifiers_in_relation10662 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0040000000000000L,0x000000000000003EL});
	public static final BitSet FOLLOW_relationType_in_relation10666 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_relation10670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_relation10690 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_K_IN_in_relation10692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_inMarker_in_relation10696 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_relation10716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_K_IN_in_relation10718 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_singleColumnInValues_in_relation10722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_relation10742 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_K_CONTAINS_in_relation10744 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_K_KEY_in_relation10749 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_relation10765 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cident_in_relation10777 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_198_in_relation10779 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_relation10783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_200_in_relation10785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0040000000000000L,0x000000000000003EL});
	public static final BitSet FOLLOW_relationType_in_relation10789 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_relation10793 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tupleOfIdentifiers_in_relation10805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L,0x0040000000000000L,0x000000000000003EL});
	public static final BitSet FOLLOW_K_IN_in_relation10815 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8080010000000000L});
	public static final BitSet FOLLOW_183_in_relation10829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_relation10831 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inMarkerForTuple_in_relation10863 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tupleOfTupleLiterals_in_relation10897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tupleOfMarkersForTuples_in_relation10931 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_relationType_in_relation10973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_tupleLiteral_in_relation10977 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_relationType_in_relation11003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_markerForTuple_in_relation11007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_relation11037 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x008002008F61DBFFL});
	public static final BitSet FOLLOW_relation_in_relation11039 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_184_in_relation11042 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_inMarker11063 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_191_in_inMarker11073 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_inMarker11077 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_tupleOfIdentifiers11109 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_tupleOfIdentifiers11113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_tupleOfIdentifiers11118 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_cident_in_tupleOfIdentifiers11122 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_tupleOfIdentifiers11128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_singleColumnInValues11158 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x918093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_singleColumnInValues11166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_singleColumnInValues11171 = new BitSet(new long[]{0x5877BB8A18D10840L,0x9A3E4AC3BB978B5EL,0x908093008F61DBFFL,0x0000000000000440L});
	public static final BitSet FOLLOW_term_in_singleColumnInValues11175 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_singleColumnInValues11184 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_tupleOfTupleLiterals11214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_tupleLiteral_in_tupleOfTupleLiterals11218 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_tupleOfTupleLiterals11223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_tupleLiteral_in_tupleOfTupleLiterals11227 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_tupleOfTupleLiterals11233 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_markerForTuple11254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_191_in_markerForTuple11264 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_markerForTuple11268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_183_in_tupleOfMarkersForTuples11300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_markerForTuple_in_tupleOfMarkersForTuples11304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_187_in_tupleOfMarkersForTuples11309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x8000010000000000L});
	public static final BitSet FOLLOW_markerForTuple_in_tupleOfMarkersForTuples11313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0900000000000000L});
	public static final BitSet FOLLOW_184_in_tupleOfMarkersForTuples11319 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QMARK_in_inMarkerForTuple11340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_191_in_inMarkerForTuple11350 = new BitSet(new long[]{0x5877BB8A18400000L,0x9A3E4283BB968B5EL,0x000002008F61D3FFL});
	public static final BitSet FOLLOW_noncol_ident_in_inMarkerForTuple11354 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_native_type_in_comparatorType11379 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_collection_type_in_comparatorType11395 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tuple_type_in_comparatorType11407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_userTypeName_in_comparatorType11423 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_FROZEN_in_comparatorType11435 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_193_in_comparatorType11437 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_comparatorType11441 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_196_in_comparatorType11443 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_comparatorType11461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_ASCII_in_native_type11490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_BIGINT_in_native_type11504 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_BLOB_in_native_type11517 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_BOOLEAN_in_native_type11532 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_COUNTER_in_native_type11544 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DECIMAL_in_native_type11556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DOUBLE_in_native_type11568 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DURATION_in_native_type11581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_FLOAT_in_native_type11594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_INET_in_native_type11608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_INT_in_native_type11623 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_SMALLINT_in_native_type11639 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TEXT_in_native_type11650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TIMESTAMP_in_native_type11665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TINYINT_in_native_type11675 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_UUID_in_native_type11687 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_VARCHAR_in_native_type11702 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_VARINT_in_native_type11714 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TIMEUUID_in_native_type11727 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_DATE_in_native_type11738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TIME_in_native_type11753 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_MAP_in_collection_type11781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_193_in_collection_type11784 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_collection_type11788 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_187_in_collection_type11790 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_collection_type11794 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_196_in_collection_type11796 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_LIST_in_collection_type11814 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_193_in_collection_type11816 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_collection_type11820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_196_in_collection_type11822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_SET_in_collection_type11840 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_193_in_collection_type11843 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_collection_type11847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_196_in_collection_type11849 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_TUPLE_in_tuple_type11880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_193_in_tuple_type11882 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_tuple_type11897 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_187_in_tuple_type11902 = new BitSet(new long[]{0x5877BB8A18400000L,0xDA3E4283BB968B5EL,0x000012008F61D3FFL});
	public static final BitSet FOLLOW_comparatorType_in_tuple_type11906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0800000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_196_in_tuple_type11918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_username11937 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_username11945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_username11953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_mbean11972 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_in_non_type_ident11997 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_NAME_in_non_type_ident12028 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_basic_unreserved_keyword_in_non_type_ident12053 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_K_KEY_in_non_type_ident12065 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unreserved_function_keyword_in_unreserved_keyword12108 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_unreserved_keyword12124 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_basic_unreserved_keyword_in_unreserved_function_keyword12175 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_native_type_in_unreserved_function_keyword12187 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_basic_unreserved_keyword12225 = new BitSet(new long[]{0x0000000000000002L});
}

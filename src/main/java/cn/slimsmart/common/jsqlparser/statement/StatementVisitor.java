/* ================================================================
 * JSQLParser : java based sql parser 
 * ================================================================
 *
 * Project Info:  http://jsqlparser.sourceforge.net
 * Project Lead:  Leonardo Francalanci (leoonardoo@yahoo.it);
 *
 * (C) Copyright 2004, by Leonardo Francalanci
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package cn.slimsmart.common.jsqlparser.statement;

import cn.slimsmart.common.jsqlparser.statement.create.table.CreateTable;
import cn.slimsmart.common.jsqlparser.statement.delete.Delete;
import cn.slimsmart.common.jsqlparser.statement.drop.Drop;
import cn.slimsmart.common.jsqlparser.statement.insert.Insert;
import cn.slimsmart.common.jsqlparser.statement.replace.Replace;
import cn.slimsmart.common.jsqlparser.statement.select.Select;
import cn.slimsmart.common.jsqlparser.statement.truncate.Truncate;
import cn.slimsmart.common.jsqlparser.statement.update.Update;

public interface StatementVisitor {
	public void visit(Select select);
	public void visit(Delete delete);
	public void visit(Update update);
	public void visit(Insert insert);
	public void visit(Replace replace);
	public void visit(Drop drop);
	public void visit(Truncate truncate);
	public void visit(CreateTable createTable);

}

/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.site.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.sakaiproject.authz.api.AuthzGroup;
import org.sakaiproject.entity.api.Edit;
import org.sakaiproject.time.api.Time;
import org.sakaiproject.user.api.User;

/**
 * <p>
 * Site is the object that knows the information, tools and layouts for a Sakai Site.
 * </p>
 */
public interface Site extends Edit, Comparable, Serializable, AuthzGroup
{
	/**
	 * @return the user who created this.
	 */
	User getCreatedBy();

	/**
	 * @return the user who last modified this.
	 */
	User getModifiedBy();

	/**
	 * @return the time created.
	 */
	Time getCreatedTime();

	/**
	 * @return the time last modified.
	 */
	Time getModifiedTime();

	/** @return The human readable Title of the site. */
	String getTitle();

	/** @return A short text Description of the site. */
	String getShortDescription();

	/** @return A longer text Description of the site. */
	String getDescription();

	/** @return The Site's icon URL. */
	String getIconUrl();

	/** @return The Site's icon URL as a full URL. */
	String getIconUrlFull();

	/** @return The Site's info display URL. */
	String getInfoUrl();

	/** @return The Site's info display URL as a full URL. */
	String getInfoUrlFull();

	/** @return true if this Site can be joined by anyone, false if not. */
	boolean isJoinable();

	/** @return the role name given to users who join a joinable site. */
	String getJoinerRole();

	/** @return the skin to use for this site. */
	String getSkin();

	/** @return the List (SitePage) of Site Pages. */
	List getPages();

	/**
	 * Make sure pages and tools, groups and properties are loaded, not lazy
	 */
	void loadAll();

	/** @return The pages ordered by the tool order constraint for this site's type (as tool category). */
	List getOrderedPages();

	/** @return true if the site is published, false if not. */
	boolean isPublished();

	/**
	 * Access the SitePage that has this id, if one is defined, else return null.
	 * 
	 * @param id
	 *        The id of the SitePage.
	 * @return The SitePage that has this id, if one is defined, else return null.
	 */
	SitePage getPage(String id);

	/**
	 * Access the ToolConfiguration that has this id, if one is defined, else return null. The tool may be on any SitePage in the site.
	 * 
	 * @param id
	 *        The id of the tool.
	 * @return The ToolConfiguration that has this id, if one is defined, else return null.
	 */
	ToolConfiguration getTool(String id);

	/**
	 * Get all the tools placed in the site on any page that are of any of these tool ids.
	 * 
	 * @param toolIds
	 *        The tool id array (String, such as sakai.chat, not a tool configuration / placement uuid) to search for.
	 * @return A Collection (ToolConfiguration) of all the tools placed in the site on any page that are of this tool id (may be empty).
	 */
	Collection getTools(String[] toolIds);

	/**
	 * Get all the tools placed in the site on any page for a particular common Tool Id.
	 * 
	 * @param commonToolId
	 *        The tool id (String, such as sakai.chat, not a tool configuration / placement uuid) to search for.
	 * @return A Collection (ToolConfiguration) of all the tools placed in the site on any page that are of this tool id (may be empty).
	 */
	Collection getTools(String commonToolId);

	/**
	 * Get the first tool placed on the site on any page with the specified common Tool id (such as sakai.chat)
	 * 
	 * @param commonToolId
	 *        The common ToolID to search for (i.e. sakai.chat)
	 * @return ToolConfiguration for the tool which has the ID (if any) or null if no tools match.
	 */
	ToolConfiguration getToolForCommonId(String commonToolId);

	/**
	 * Access the site type.
	 * 
	 * @return The site type.
	 */
	String getType();

	/**
	 * Test if the site is of this type. It is if the param is null.
	 * 
	 * @param type
	 *        A String type to match, or a String[], List or Set of Strings, any of which can match.
	 * @return true if the site is of the type(s) specified, false if not.
	 */
	boolean isType(Object type);

	/**
	 * Check if the site is marked for viewing.
	 * 
	 * @return True if the site is marked for viewing, false if not
	 */
	boolean isPubView();

	/**
	 * Get a site group
	 * 
	 * @param id
	 *        The group id (or reference).
	 * @return The Group object if found, or null if not found.
	 */
	Group getGroup(String id);

	/**
	 * Get a collection of the groups in a Site.
	 * 
	 * @return A collection (Group) of groups defined in the site, empty if there are none.
	 */
	Collection getGroups();

	/**
	 * Get a collection of the groups in a Site that have this user as a member.
	 * 
	 * @param userId
	 *        The user id.
	 * @return A collection (Group) of groups defined in the site that have this user as a member, empty if there are none.
	 */
	Collection getGroupsWithMember(String userId);

	/**
	 * Get a collection of the groups in a Site that have this user as a member with this role.
	 * 
	 * @param userId
	 *        The user id.
	 * @param role
	 *        The role.
	 * @return A collection (Group) of groups defined in the site that have this user as a member with this role, empty if there are none.
	 */
	Collection getGroupsWithMemberHasRole(String userId, String role);

	/**
	 * Does the site have any groups defined?
	 * 
	 * @return true if the site and has any groups, false if not.
	 */
	boolean hasGroups();

	/**
	 * Set the human readable Title of the site.
	 * 
	 * @param title
	 *        the new title.
	 */
	void setTitle(String title);

	/**
	 * Set the url of an icon for the site.
	 * 
	 * @param url
	 *        The new icon's url.
	 */
	void setIconUrl(String url);

	/**
	 * Set the url for information about the site.
	 * 
	 * @param url
	 *        The new information url.
	 */
	void setInfoUrl(String url);

	/**
	 * Set the joinable status of the site.
	 * 
	 * @param joinable
	 *        represents whether the site is joinable (true) or not (false).
	 */
	void setJoinable(boolean joinable);

	/**
	 * Set the joiner role for a site.
	 * 
	 * @param role
	 *        the joiner role for a site.
	 */
	void setJoinerRole(String role);

	/**
	 * Set the short Description of the site. Used to give a short text description of the site.
	 * 
	 * @param description
	 *        The new short description.
	 */
	void setShortDescription(String description);

	/**
	 * Set the Description of the site. Used to give a longer text description of the site.
	 * 
	 * @param description
	 *        The new description.
	 */
	void setDescription(String description);

	/**
	 * Set the published state of this site.
	 * 
	 * @param status
	 *        The published state of the site.
	 */
	void setPublished(boolean published);

	/**
	 * Set the skin to use for this site.
	 * 
	 * @param skin
	 *        The skin to use for this site.
	 */
	void setSkin(String skin);

	/**
	 * Create a new site page and add it to this site.
	 * 
	 * @return The SitePage object for the new site page.
	 */
	SitePage addPage();

	/**
	 * Remove a site page from this site.
	 * 
	 * @param page
	 *        The SitePage to remove.
	 */
	void removePage(SitePage page);

	/**
	 * Generate a new set of pages and tools that have new, unique ids. Good if the site had non-unique-system-wide ids for pages and tools. The Site Id does not change.
	 */
	void regenerateIds();

	/**
	 * Set the site type.
	 * 
	 * @param type
	 *        The site type.
	 */
	void setType(String type);

	/**
	 * Set the site view.
	 * 
	 * @param pubView
	 *        The site view setting.
	 */
	void setPubView(boolean pubView);

	/**
	 * Add a new group. The Id is generated, the rest of the fields can be set using calls to the Group object returned.
	 */
	Group addGroup();

	/**
	 * Remove this group from the groups for this site.
	 * 
	 * @param group
	 *        The group to remove.
	 */
	void removeGroup(Group group);
}
